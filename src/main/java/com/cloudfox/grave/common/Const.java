package com.cloudfox.grave.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String CUSTOMER = "customer";

    public static final String RESET_PASSWORD = "123456";

    public enum CommonStatus {
        DELETE(-1,"删除"),
        FROZEN(0,"冻结"),
        COMMON(1,"正常");

        private int code;

        private String desc;

        CommonStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDesc(int code) {
            for (CommonStatus c: CommonStatus.values()) {
                if(c.getCode() == code) {
                    return c.getDesc();
                }
            }
            return "";
        }

    }

    public enum AdminList {
        ADMINHEAD(1,"总管理员");

        private int code;

        private String desc;

        AdminList(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

    }

    public enum TributeTypeEnum {
        free(0,"免费"),
        charge(1,"收费");

        private int code;

        private String desc;

        TributeTypeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDesc(int code) {
            for (TributeTypeEnum c: TributeTypeEnum.values()) {
                if(c.getCode() == code) {
                    return c.getDesc();
                }
            }
            return "";
        }

    }

    public enum TributeUseEnum {
        down(0,"下架"),
        up(1,"上架");

        private int code;

        private String desc;

        TributeUseEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDesc(int code) {
            for (TributeUseEnum c: TributeUseEnum.values()) {
                if(c.getCode() == code) {
                    return c.getDesc();
                }
            }
            return "";
        }

    }
}
