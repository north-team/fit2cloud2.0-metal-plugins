package com.fit2cloud.metal.sdk.model;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class F2CPmNetworkCard implements Serializable {
    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("VlanId")
    private String vlanId;

    @ApiModelProperty("IP地址")
    private String ip;

    @ApiModelProperty("编号")
    private String number;

    @ApiModelProperty("物理机ID")
    private String physicalMachineId;

    @ApiModelProperty("mac地址")
    private String mac;

    private Long synTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pm_network_card
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_network_card.id
     *
     * @return the value of pm_network_card.id
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_network_card.id
     *
     * @param id the value for pm_network_card.id
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_network_card.vlan_id
     *
     * @return the value of pm_network_card.vlan_id
     * @mbg.generated
     */
    public String getVlanId() {
        return vlanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_network_card.vlan_id
     *
     * @param vlanId the value for pm_network_card.vlan_id
     * @mbg.generated
     */
    public void setVlanId(String vlanId) {
        this.vlanId = vlanId == null ? null : vlanId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_network_card.ip
     *
     * @return the value of pm_network_card.ip
     * @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_network_card.ip
     *
     * @param ip the value for pm_network_card.ip
     * @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_network_card.number
     *
     * @return the value of pm_network_card.number
     * @mbg.generated
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_network_card.number
     *
     * @param number the value for pm_network_card.number
     * @mbg.generated
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_network_card.physical_machine_id
     *
     * @return the value of pm_network_card.physical_machine_id
     * @mbg.generated
     */
    public String getPhysicalMachineId() {
        return physicalMachineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_network_card.physical_machine_id
     *
     * @param physicalMachineId the value for pm_network_card.physical_machine_id
     * @mbg.generated
     */
    public void setPhysicalMachineId(String physicalMachineId) {
        this.physicalMachineId = physicalMachineId == null ? null : physicalMachineId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_network_card.mac
     *
     * @return the value of pm_network_card.mac
     * @mbg.generated
     */
    public String getMac() {
        return mac;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_network_card.mac
     *
     * @param mac the value for pm_network_card.mac
     * @mbg.generated
     */
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Long getSynTime() {
        return synTime;
    }

    public void setSynTime(Long synTime) {
        this.synTime = synTime;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}