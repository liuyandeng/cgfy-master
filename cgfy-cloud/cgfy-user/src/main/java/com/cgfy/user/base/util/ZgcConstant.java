package com.cgfy.user.base.util;

public class ZgcConstant {
    //数据字典code类型--------------------------------------------
    private static final String status_wf = "status_wf";
    private static final String type= "type";
    private static final String urgency= "urgency";
    private static final String direction= "direction";
    private static final String incoming_type= "incoming_type";
    private static final String coming_org= "coming_org";
    private static final String emergencyLevel= "emergencyLevel";
    private static final String contract= "contract";
    private static final String feedback= "feedback";
    private static final String status_simple= "status_simple";
    private static final String purpose= "purpose";
    private static final String status_private= "status_private";
    private static final String clearing= "clearing";
    private static final String topPower= "topPower";
    private static final String currency= "currency";
    private static final String status_common= "status_common";
    private static final String info_type= "info_type";
    private static final String vehicle_type= "vehicle_type";
    private static final String vehicle_status= "vehicle_status";
    private static final String driver_status= "driver_status";
    private static final String branding_status= "branding_status";
    private static final String status_resource= "status_resource";

    private static final String job= "job";



    private static final String borrow_clearing= "borrow_clearing";
    //删除和保存状态-------------------------------------------------------------------
    private static final String deleteStatus= "-1";
    private static final String savedStatus= "0";
    //工作流业务分类ID--------------------------------------------------------------
    private static final String outgoingTypeId= "outgoing";

    //董事会
    private static final String boardMeetingTypeId="boardMeeting";
    //总办会
    private static final String gmoMeetingFormsTypeId="gmoMeeting";
    //党委会
    private static final String partyMeetingInfoTypeId="partyMeeting";
    //专题会
    private static final String specMeetingFormsTypeId="specMeeting";

    //代理开启关闭状态-------------------------------------------------------------------
    private static final String agentOpenStatus= "1";
    private static final String agentCloseStatus= "0";

    public static String getBoardMeetingTypeId() {
        return boardMeetingTypeId;
    }

    public static String getStatus_wf() {
        return status_wf;
    }

    public static String getType() {
        return type;
    }

    public static String getUrgency() {
        return urgency;
    }

    public static String getDirection() {
        return direction;
    }

    public static String getIncoming_type() {
        return incoming_type;
    }

    public static String getComing_org() {
        return coming_org;
    }

    public static String getEmergencyLevel() {
        return emergencyLevel;
    }

    public static String getContract() {
        return contract;
    }

    public static String getFeedback() {
        return feedback;
    }

    public static String getStatus_simple() {
        return status_simple;
    }

    public static String getPurpose() {
        return purpose;
    }

    public static String getStatus_private() {
        return status_private;
    }

    public static String getClearing() {
        return clearing;
    }

    public static String getTopPower() {
        return topPower;
    }

    public static String getCurrency() {
        return currency;
    }

    public static String getStatus_common() {
        return status_common;
    }

    public static String getInfo_type() {
        return info_type;
    }

    public static String getVehicle_type() {
        return vehicle_type;
    }

    public static String getVehicle_status() {
        return vehicle_status;
    }

    public static String getDriver_status() {
        return driver_status;
    }

    public static String getBranding_status() {
        return branding_status;
    }

    public static String getDeleteStatus() {
        return deleteStatus;
    }

    public static String getSavedStatus() {
        return savedStatus;
    }

    public static String getOutgoingTypeId() {
        return outgoingTypeId;
    }
    public static String getJob() {
        return job;
    }
    public static String getBorrow_clearing() {
        return borrow_clearing;
    }

    public static String getGmoMeetingFormsTypeId() {
        return gmoMeetingFormsTypeId;
    }

    public static String getPartyMeetingInfoTypeId() {
        return partyMeetingInfoTypeId;
    }

    public static String getSpecMeetingFormsTypeId() {
        return specMeetingFormsTypeId;
    }

    public static String getStatus_resource() {
        return status_resource;
    }

    public static String getAgentOpenStatus() {
        return agentOpenStatus;
    }

    public static String getAgentCloseStatus() {
        return agentCloseStatus;
    }
}
