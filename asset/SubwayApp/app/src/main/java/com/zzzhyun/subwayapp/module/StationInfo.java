package com.zzzhyun.subwayapp.module;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ZHYUN on 2017-10-19.
 */

@DatabaseTable(tableName = "TB_STATION_INFO")
public class StationInfo
{
    @DatabaseField
    private String statnFid;

    @DatabaseField
    private String lastRecptnDt;

    @DatabaseField
    private String selectedCount;

    @DatabaseField
    private String directAt;

    @DatabaseField
    private String telno;

    @DatabaseField
    private String subwayList;

    @DatabaseField
    private String ord;

    @DatabaseField
    private String trainNo;

// 역 아이디
@DatabaseField
    private String statnTid;

    @DatabaseField
    private String sctnDstnc;

    @DatabaseField
    private String crosngAt;

    @DatabaseField
    private String subwayId;

    @DatabaseField
    private String adresDetail;

    @DatabaseField
    private String totalCount;

    @DatabaseField
    private String curPage;

    @DatabaseField
    private String sctnTime;

    @DatabaseField
    private String statnNm;

    @DatabaseField
    private String statnTnm;

    @DatabaseField
    private String imageY;

    @DatabaseField
    private String pltfomSe;

    @DatabaseField
    private String imageX;

    @DatabaseField
    private String gffDoor;

    @DatabaseField
    private String statnNmEng;

    @DatabaseField
    private String adresBass;

    @DatabaseField
    private String trainSttus;

    @DatabaseField
    private String dspsnCvntlAt;

    @DatabaseField
    private String operPblinstt;

    @DatabaseField
    private String zipNo;

    @DatabaseField
    private String subwayYcnts;

    @DatabaseField
    private String beginRow;

    @DatabaseField
    private String statnFnm;

    @DatabaseField
    private String fxnum;

    @DatabaseField
    private String trttprkAt;

    @DatabaseField
    private String subwayXcnts;

    @DatabaseField
    private String bcyclCstdyAt;

    @DatabaseField
    private String endRow;

    @DatabaseField
    private String trnsitCo;

    @DatabaseField
    private String updnLine;

    @DatabaseField
    private String lstcarAt;

    @DatabaseField
    private String toiletAt;

    @DatabaseField
    private String statnSn;

    @DatabaseField
    private String rowNum;

    @DatabaseField
    private String statnId;

    @DatabaseField
    private String pageRow;

    // 호선 명
    @DatabaseField
    private String subwayNm;

    public StationInfo() {
    }

    public String getStatnFid ()
    {
        return statnFid;
    }

    public void setStatnFid (String statnFid)
    {
        this.statnFid = statnFid;
    }

    public String getLastRecptnDt ()
{
    return lastRecptnDt;
}

    public void setLastRecptnDt (String lastRecptnDt)
    {
        this.lastRecptnDt = lastRecptnDt;
    }

    public String getSelectedCount ()
    {
        return selectedCount;
    }

    public void setSelectedCount (String selectedCount)
    {
        this.selectedCount = selectedCount;
    }

    public String getDirectAt ()
    {
        return directAt;
    }

    public void setDirectAt (String directAt)
    {
        this.directAt = directAt;
    }

    public String getTelno ()
    {
        return telno;
    }

    public void setTelno (String telno)
    {
        this.telno = telno;
    }

    public String getSubwayList ()
    {
        return subwayList;
    }

    public void setSubwayList (String subwayList)
    {
        this.subwayList = subwayList;
    }

    public String getOrd ()
{
    return ord;
}

    public void setOrd (String ord)
    {
        this.ord = ord;
    }

    public String getTrainNo ()
{
    return trainNo;
}

    public void setTrainNo (String trainNo)
    {
        this.trainNo = trainNo;
    }

    public String getStatnTid ()
    {
        return statnTid;
    }

    public void setStatnTid (String statnTid)
    {
        this.statnTid = statnTid;
    }

    public String getSctnDstnc ()
{
    return sctnDstnc;
}

    public void setSctnDstnc (String sctnDstnc)
    {
        this.sctnDstnc = sctnDstnc;
    }

    public String getCrosngAt ()
{
    return crosngAt;
}

    public void setCrosngAt (String crosngAt)
    {
        this.crosngAt = crosngAt;
    }

    public String getSubwayId ()
    {
        return subwayId;
    }

    public void setSubwayId (String subwayId)
    {
        this.subwayId = subwayId;
    }

    public String getAdresDetail ()
    {
        return adresDetail;
    }

    public void setAdresDetail (String adresDetail)
    {
        this.adresDetail = adresDetail;
    }

    public String getTotalCount ()
    {
        return totalCount;
    }

    public void setTotalCount (String totalCount)
    {
        this.totalCount = totalCount;
    }

    public String getCurPage ()
{
    return curPage;
}

    public void setCurPage (String curPage)
    {
        this.curPage = curPage;
    }

    public String getSctnTime ()
{
    return sctnTime;
}

    public void setSctnTime (String sctnTime)
    {
        this.sctnTime = sctnTime;
    }

    public String getStatnNm ()
    {
        return statnNm;
    }

    public void setStatnNm (String statnNm)
    {
        this.statnNm = statnNm;
    }

    public String getStatnTnm ()
    {
        return statnTnm;
    }

    public void setStatnTnm (String statnTnm)
    {
        this.statnTnm = statnTnm;
    }

    public String getImageY ()
{
    return imageY;
}

    public void setImageY (String imageY)
    {
        this.imageY = imageY;
    }

    public String getPltfomSe ()
{
    return pltfomSe;
}

    public void setPltfomSe (String pltfomSe)
    {
        this.pltfomSe = pltfomSe;
    }

    public String getImageX ()
{
    return imageX;
}

    public void setImageX (String imageX)
    {
        this.imageX = imageX;
    }

    public String getGffDoor ()
{
    return gffDoor;
}

    public void setGffDoor (String gffDoor)
    {
        this.gffDoor = gffDoor;
    }

    public String getStatnNmEng ()
    {
        return statnNmEng;
    }

    public void setStatnNmEng (String statnNmEng)
    {
        this.statnNmEng = statnNmEng;
    }

    public String getAdresBass ()
    {
        return adresBass;
    }

    public void setAdresBass (String adresBass)
    {
        this.adresBass = adresBass;
    }

    public String getTrainSttus ()
{
    return trainSttus;
}

    public void setTrainSttus (String trainSttus)
    {
        this.trainSttus = trainSttus;
    }

    public String getDspsnCvntlAt ()
{
    return dspsnCvntlAt;
}

    public void setDspsnCvntlAt (String dspsnCvntlAt)
    {
        this.dspsnCvntlAt = dspsnCvntlAt;
    }

    public String getOperPblinstt ()
    {
        return operPblinstt;
    }

    public void setOperPblinstt (String operPblinstt)
    {
        this.operPblinstt = operPblinstt;
    }

    public String getZipNo ()
    {
        return zipNo;
    }

    public void setZipNo (String zipNo)
    {
        this.zipNo = zipNo;
    }

    public String getSubwayYcnts ()
    {
        return subwayYcnts;
    }

    public void setSubwayYcnts (String subwayYcnts)
    {
        this.subwayYcnts = subwayYcnts;
    }

    public String getBeginRow ()
{
    return beginRow;
}

    public void setBeginRow (String beginRow)
    {
        this.beginRow = beginRow;
    }

    public String getStatnFnm ()
    {
        return statnFnm;
    }

    public void setStatnFnm (String statnFnm)
    {
        this.statnFnm = statnFnm;
    }

    public String getFxnum ()
    {
        return fxnum;
    }

    public void setFxnum (String fxnum)
    {
        this.fxnum = fxnum;
    }

    public String getTrttprkAt ()
{
    return trttprkAt;
}

    public void setTrttprkAt (String trttprkAt)
    {
        this.trttprkAt = trttprkAt;
    }

    public String getSubwayXcnts ()
    {
        return subwayXcnts;
    }

    public void setSubwayXcnts (String subwayXcnts)
    {
        this.subwayXcnts = subwayXcnts;
    }

    public String getBcyclCstdyAt ()
{
    return bcyclCstdyAt;
}

    public void setBcyclCstdyAt (String bcyclCstdyAt)
    {
        this.bcyclCstdyAt = bcyclCstdyAt;
    }

    public String getEndRow ()
{
    return endRow;
}

    public void setEndRow (String endRow)
    {
        this.endRow = endRow;
    }

    public String getTrnsitCo ()
    {
        return trnsitCo;
    }

    public void setTrnsitCo (String trnsitCo)
    {
        this.trnsitCo = trnsitCo;
    }

    public String getUpdnLine ()
{
    return updnLine;
}

    public void setUpdnLine (String updnLine)
    {
        this.updnLine = updnLine;
    }

    public String getLstcarAt ()
{
    return lstcarAt;
}

    public void setLstcarAt (String lstcarAt)
    {
        this.lstcarAt = lstcarAt;
    }

    public String getToiletAt ()
{
    return toiletAt;
}

    public void setToiletAt (String toiletAt)
    {
        this.toiletAt = toiletAt;
    }

    public String getStatnSn ()
{
    return statnSn;
}

    public void setStatnSn (String statnSn)
    {
        this.statnSn = statnSn;
    }

    public String getRowNum ()
    {
        return rowNum;
    }

    public void setRowNum (String rowNum)
    {
        this.rowNum = rowNum;
    }

    public String getStatnId ()
    {
        return statnId;
    }

    public void setStatnId (String statnId)
    {
        this.statnId = statnId;
    }

    public String getPageRow ()
{
    return pageRow;
}

    public void setPageRow (String pageRow)
    {
        this.pageRow = pageRow;
    }

    public String getSubwayNm ()
    {
        return subwayNm;
    }

    public void setSubwayNm (String subwayNm)
    {
        this.subwayNm = subwayNm;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [statnFid = "+statnFid+", lastRecptnDt = "+lastRecptnDt+", selectedCount = "+selectedCount+", directAt = "+directAt+", telno = "+telno+", subwayList = "+subwayList+", ord = "+ord+", trainNo = "+trainNo+", statnTid = "+statnTid+", sctnDstnc = "+sctnDstnc+", crosngAt = "+crosngAt+", subwayId = "+subwayId+", adresDetail = "+adresDetail+", totalCount = "+totalCount+", curPage = "+curPage+", sctnTime = "+sctnTime+", statnNm = "+statnNm+", statnTnm = "+statnTnm+", imageY = "+imageY+", pltfomSe = "+pltfomSe+", imageX = "+imageX+", gffDoor = "+gffDoor+", statnNmEng = "+statnNmEng+", adresBass = "+adresBass+", trainSttus = "+trainSttus+", dspsnCvntlAt = "+dspsnCvntlAt+", operPblinstt = "+operPblinstt+", zipNo = "+zipNo+", subwayYcnts = "+subwayYcnts+", beginRow = "+beginRow+", statnFnm = "+statnFnm+", fxnum = "+fxnum+", trttprkAt = "+trttprkAt+", subwayXcnts = "+subwayXcnts+", bcyclCstdyAt = "+bcyclCstdyAt+", endRow = "+endRow+", trnsitCo = "+trnsitCo+", updnLine = "+updnLine+", lstcarAt = "+lstcarAt+", toiletAt = "+toiletAt+", statnSn = "+statnSn+", rowNum = "+rowNum+", statnId = "+statnId+", pageRow = "+pageRow+", subwayNm = "+subwayNm+"]";
    }
}