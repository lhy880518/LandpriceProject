package com.landprice.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LandVo {

    @SerializedName("response")
    public Response response;

    public class Response{

        @SerializedName("header")
        public header header;

        @SerializedName("body")
        public body body;
    }

    public class header{

        @SerializedName("resultCode")
        public String resultCode;

        @SerializedName("resultMsg")
        public String resultMsg;

    }

    public class body{

        @SerializedName("items")
        public items items;

        @SerializedName("numOfRows")
        public Integer numOfRows;

        @SerializedName("pageNo")
        public Integer pageNo;

        @SerializedName("totalCount")
        public Integer totalCount;

    }

    public class items{

        @SerializedName("item")
        public item[] item;

    }

    public class item{

        @SerializedName("거래금액")
        public String price;

        @SerializedName("거래면적")
        public Integer area;

        @SerializedName("구분")
        public String kind;

        @SerializedName("년")
        public Integer year;

        @SerializedName("법정동")
        public String bubjung;

        @SerializedName("시군구")
        public String sigun;

        @SerializedName("용도지역")
        public String areaKind;

        @SerializedName("월")
        public Integer month;

        @SerializedName("일")
        public String day;

        @SerializedName("지목")
        public String gimok;

        @SerializedName("지역코드")
        public Integer areaCode;
    }
}
