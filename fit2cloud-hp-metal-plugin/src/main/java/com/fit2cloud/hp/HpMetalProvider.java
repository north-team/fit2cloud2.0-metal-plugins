package com.fit2cloud.hp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.hp.model.*;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.F2CResourceType;
import com.fit2cloud.metal.sdk.constants.InitMethod;
import com.fit2cloud.metal.sdk.constants.PluginConstants;
import com.fit2cloud.metal.sdk.model.*;
import com.fit2cloud.metal.sdk.util.DiskUtil;
import com.fit2cloud.metal.sdk.util.ExceptionDetailUtils;
import com.fit2cloud.metal.sdk.util.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@F2CMetalPlugin
public class HpMetalProvider extends AbstractMetalProvider {
    private static String name = "fit2cloud-hp-metal-plugin";
    Logger logger = LoggerFactory.getLogger(HpMetalProvider.class);

    //web控制台 Http接口
    private static final String loginUrl = "https://%s/json/login_session";
    private static final String overViewUrl = "https://%s/json/overview";
    private static final String mem_infoUrl = "https://%s/json/mem_info";
    private static final String proc_infoUrl = "https://%s/json/proc_info";
    private static final String health_drivesUrl = "https://%s/json/health_drives";
    private static final String health_phy_drivesUrl = "https://%s/json/health_phy_drives";
    private static final String phy_nic_infoUrl = "https://%s/json/phy_nic_info";
    private static final String power_infoUrl = "https://%s/json/power_info";

    private static final ConcurrentHashMap<String, Map<String, String>> headersMap = new ConcurrentHashMap();
    private Gson gson = new Gson();

    public String getName() {
        return name;
    }

    @Override
    public List<InitMethod> getSupportedInitMethod(String request) throws MetalPluginException {
        return Arrays.asList(InitMethod.LOGIN, InitMethod.RESTAPI, InitMethod.LOGOUT, InitMethod.SNMP, InitMethod.IPMI);
    }

    @Override
    public String getPlatformVersion(String request) throws MetalPluginException {
        return "暂时不支持该方法！";
    }

    @Override
    public MachineEntity getMachineEntity(String ipmiSnmpRequestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiSnmpRequestStr, IPMIRequest.class);
        String ip = request.getIp();
        checkIPMIRequest(request);
        try {
            if (login(ipmiSnmpRequestStr)) {
                Map header = headersMap.get(request.getIp());
                MachineEntity entity = new MachineEntity();

                HpOverViewDTO hpOverViewDTO = gson.fromJson(HttpUtils.get(String.format(overViewUrl, ip), header), HpOverViewDTO.class);
                entity.setBmcIp(request.getIp());
                entity.setBrand(PluginConstants.HP);
                entity.setSerialNo(hpOverViewDTO.getSerialNum());
                entity.setName(PluginConstants.HP + " " + hpOverViewDTO.getProductName());

                List<F2CCpu> cpus = new LinkedList<>();
                List<F2CMemory> memories = new LinkedList<>();
                List<F2CPhysicalDisk> disks = new LinkedList<>();
                List<F2CPmNetworkCard> pmNetworkCards = new LinkedList<>();

                //cpu
                HpCpuDTO hpCpuDTO = gson.fromJson(HttpUtils.get(String.format(proc_infoUrl, ip), header), HpCpuDTO.class);
                if (hpCpuDTO != null && hpCpuDTO.getProcessors() != null && hpCpuDTO.getProcessors().size() > 0) {
                    hpCpuDTO.getProcessors().forEach(c -> {
                        if (StringUtils.isBlank(c.getProcName())) {
                            return;
                        }
                        F2CCpu cpu = new F2CCpu();
                        cpu.setProcName(c.getProcName());
                        cpu.setProcSocket(c.getProcSocket());
                        cpu.setProcSpeed(String.valueOf(c.getProcSpeed() == 0 ? null : c.getProcSpeed()));
                        cpu.setProcNumCores(String.valueOf(c.getProcNumCores() == 0 ? null : c.getProcNumCores()));
                        cpu.setProcNumCoresEnabled(String.valueOf(c.getProcNumCoresEnabled() == 0 ? null : c.getProcNumCoresEnabled()));
                        cpu.setProcNumThreads(String.valueOf(c.getProcNumThreads() == 0 ? null : c.getProcNumThreads()));
                        cpu.setProcNumL1cache(String.valueOf(c.getProcNumL1cache() == 0 ? null : c.getProcNumL1cache()));
                        cpu.setProcNumL2cache(String.valueOf(c.getProcNumL2cache() == 0 ? null : c.getProcNumL2cache()));
                        cpu.setProcNumL3cache(String.valueOf(c.getProcNumL3cache() == 0 ? null : c.getProcNumL3cache()));
                        cpus.add(cpu);
                    });
                }

                //memory
                HpMemDTO hpMemDTO = gson.fromJson(HttpUtils.get(String.format(mem_infoUrl, ip), header), HpMemDTO.class);
                if (hpMemDTO != null && hpMemDTO.getMemModules() != null) {
                    hpMemDTO.getMemModules().forEach(m -> {
                        if (m.getMemModSize() == 0) {
                            return;
                        }
                        F2CMemory memory = new F2CMemory();
                        memory.setMemModSize(String.valueOf(m.getMemModSize() == 0 ? 0 : m.getMemModSize()));
                        memory.setMemModNum(String.valueOf(m.getMemModNum() == 0 ? 0 : m.getMemModNum()));
                        memory.setMemModFrequency(String.valueOf(m.getMemModFrequency() == 0 ? 0 : m.getMemModFrequency()));
                        memory.setMemCpuNum(String.valueOf(m.getMemCpuNum() == 0 ? 0 : m.getMemCpuNum()));
                        memory.setMemModMinVolt(String.valueOf(m.getMemModMinVolt() == 0 ? 0 : m.getMemModMinVolt()));
                        memory.setMemModPartNumber(m.getMemModPartNum());
                        memories.add(memory);
                    });
                }

                //disk
                //插槽和raid的映射
                Map<Integer, String> slotRaidMap = new HashMap<>();
                HpVirtualDriveDTO hpVirtualDriveDTO = gson.fromJson(HttpUtils.get(String.format(health_drivesUrl, ip), header), HpVirtualDriveDTO.class);
                if (hpVirtualDriveDTO != null && hpVirtualDriveDTO.getLogDriveArrays() != null) {
                    hpVirtualDriveDTO.getLogDriveArrays().forEach(v -> {
                        v.getLogicalDrives().forEach(l -> {
                            l.getPhysicalDrives().forEach(d -> {
                                slotRaidMap.put(d, l.getFltTol());
                            });
                        });
                    });
                }

                HpPhyDriveDTO hpPhyDriveDTO = gson.fromJson(HttpUtils.get(String.format(health_phy_drivesUrl, ip), header), HpPhyDriveDTO.class);
                if (hpPhyDriveDTO != null && hpPhyDriveDTO.getPhyDriveArrays() != null) {
                    hpPhyDriveDTO.getPhyDriveArrays().forEach(pd -> {
                        pd.getPhysicalDrives().forEach(d -> {
                            F2CPhysicalDisk disk = new F2CPhysicalDisk();
                            disk.setControllerId(0);
                            disk.setEnclosureId(0);
                            //拼接raid
                            String location[] = d.getLocation().split(" ");
                            if (location.length > 5) {
                                disk.setDrive(location[1] + ":" + location[3] + ":" + location[5]);
                            } else {
                                disk.setDrive("");
                            }
                            disk.setRaid(slotRaidMap.get(d.getPhysIdx()));
                            disk.setType(d.getDriveType() != null ? d.getDriveType().contains("PHY") ? "SAS" : "SSD" : "SAS");
                            disk.setModel(d.getModel());
                            disk.setSn(d.getSerialNo());
                            disk.setSize(DiskUtil.getStandSize(d.getCapacity()));
                            disks.add(disk);
                        });
                    });
                }

                // nics
                HpPhyNicDTO hpPhyNicDTO = gson.fromJson(HttpUtils.get(String.format(phy_nic_infoUrl, ip), header), HpPhyNicDTO.class);
                if (hpPhyNicDTO != null && hpPhyNicDTO.getPhyNics() != null) {
                    hpPhyNicDTO.getPhyNics().forEach(n -> {
                        n.getPorts().forEach(p -> {
                            if (n.getName().startsWith("iLo")) return;
                            F2CPmNetworkCard networkCard = new F2CPmNetworkCard();
                            networkCard.setMac(p.getMacAddr());
                            networkCard.setNumber("eth" + p.getPortNum());
                            pmNetworkCards.add(networkCard);
                        });
                    });
                }

                entity.setCpus(cpus);
                entity.setMemories(memories);
                entity.setDisks(disks);
                entity.setPmNetworkCards(pmNetworkCards);
                logout(ipmiSnmpRequestStr);

                return entity;
            }
        } catch (Exception e) {
            logger.error(String.format("获取物理机%s硬件信息失败！%s", ip, ExceptionDetailUtils.getStackTrace(e)));
            logout(ipmiSnmpRequestStr);
        }
        return null;
    }

    @Override
    public JSONObject getRaidPayload(String raidConfigDTOStr) throws MetalPluginException {

        //:todo 装配成rackhd 能支持的格式 注意多raid阵列支持
        F2CRaidConfigDTO raidConfigDTO = gson.fromJson(raidConfigDTOStr, F2CRaidConfigDTO.class);
        JSONObject raidPayload = JSONObject.parseObject(getPageTemplate());
        JSONObject createRaid = raidPayload.getJSONObject("options").getJSONObject("create-raid");
        JSONArray raidList = new JSONArray();

        for (F2CRaidConfigDTO.RaidConfig c : raidConfigDTO.getRaidConfigList()) {
            JSONObject raidConfigObj = new JSONObject();
            raidConfigObj.put("type", getValidRaidType(c.getRaid()));
            raidConfigObj.put("drives", c.getDisks().stream().sorted(Comparator.comparing(F2CPhysicalDisk::getDrive)).map(d -> d.getDrive()).reduce(" ", String::concat).trim());
            raidList.add(raidConfigObj);
        }
        createRaid.put("raidList", raidList);
        return raidPayload;
    }

    @Override
    public JSONObject getDeletePayload() {
        try {
            //:todo 装配成rackhd 能支持的格式
            return JSONObject.parseObject(getPageTemplate(F2CResourceType.RACKHD_RAID_DEL_PAYLOAD));
        } catch (MetalPluginException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @Override
    public String getRaidWorkFlow() {
        return workflowPostUrl + "Graph.Raid.Create.HpssaRAID";
    }

    @Override
    public String getDeleteRaidWorkFlow() {
        return workflowPostUrl + "Graph.Raid.Delete.HpssaRAID";
    }

    @Override
    public String getCatalogRaidWorkFlow() {
        return workflowPostUrl + "Graph.HP.ssacli.Catalog";
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        if (raidType.equalsIgnoreCase("raid10")) {
            return "1+0";
        }
        return raidType.replace("raid", "");
    }

    @Override
    public boolean login(String ipmiRequest) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequest, IPMIRequest.class);
        checkIPMIRequest(request);
        //:todo 登录该机型的http方法
        String ip = request.getIp();
        String userName = request.getUserName();
        String pwd = request.getPassword();
        if (headersMap.get(ip) == null) {
            //{"method":"login","user_login":"administrator","password":"Fit2cloud@2019s"}
            JSONObject loginParam = new JSONObject();
            loginParam.put("method", "login");
            loginParam.put("user_login", userName);
            loginParam.put("password", pwd);

            JSONObject res = JSONObject.parseObject(HttpUtils.post(String.format(loginUrl, ip), loginParam.toJSONString()));
            if (res.containsKey("session_key")) {
                Map header = new HashMap<String, String>();
                header.put("Cookie", String.format("sessionKey=%s", res.getString("session_key")));
                headersMap.put(ip, header);
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean logout(String ipmiRequest) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequest, IPMIRequest.class);
        //:todo 登出该机型的http方法
        if (headersMap.get(request.getIp()) == null) {
            return true;
        }
        JSONObject req = new JSONObject();
        req.put("method", "logout");
        req.put("session_key", headersMap.get(request.getIp()));
        HttpUtils.post(String.format(String.format(loginUrl, request.getIp()), request.getIp()), req.toJSONString(), headersMap.get(request.getIp()));
        headersMap.remove(request.getIp());
        return true;
    }

    @Override
    public F2CMetrics getMetrics(String ipmiReqeuestStr) throws MetalPluginException {
        return null;
    }

}
