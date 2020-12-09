package com.module.core_http.http.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @autor: kifer
 * @date:2020/12/9
 * @desc: todo
 */
public class DemoBean implements Serializable {


    /**
     * tabInfo : {"tabList":[{"id":0,"name":"周排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"月排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=monthly","tabType":0,"nameType":0,"adTrack":null},{"id":2,"name":"总排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=historical","tabType":0,"nameType":0,"adTrack":null}],"defaultIdx":0}
     */

    private TabInfoBean tabInfo;

    public TabInfoBean getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfoBean tabInfo) {
        this.tabInfo = tabInfo;
    }

    public static class TabInfoBean implements  Serializable{
        /**
         * tabList : [{"id":0,"name":"周排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"月排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=monthly","tabType":0,"nameType":0,"adTrack":null},{"id":2,"name":"总排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=historical","tabType":0,"nameType":0,"adTrack":null}]
         * defaultIdx : 0
         */

        private int defaultIdx;
        private List<TabListBean> tabList;

        public int getDefaultIdx() {
            return defaultIdx;
        }

        public void setDefaultIdx(int defaultIdx) {
            this.defaultIdx = defaultIdx;
        }

        public List<TabListBean> getTabList() {
            return tabList;
        }

        public void setTabList(List<TabListBean> tabList) {
            this.tabList = tabList;
        }

        public static class TabListBean {
            /**
             * id : 0
             * name : 周排行
             * apiUrl : http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly
             * tabType : 0
             * nameType : 0
             * adTrack : null
             */

            private int id;
            private String name;
            private String apiUrl;
            private int tabType;
            private int nameType;
            private Object adTrack;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getApiUrl() {
                return apiUrl;
            }

            public void setApiUrl(String apiUrl) {
                this.apiUrl = apiUrl;
            }

            public int getTabType() {
                return tabType;
            }

            public void setTabType(int tabType) {
                this.tabType = tabType;
            }

            public int getNameType() {
                return nameType;
            }

            public void setNameType(int nameType) {
                this.nameType = nameType;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }
        }
    }
}
