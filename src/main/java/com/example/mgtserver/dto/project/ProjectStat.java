package com.example.mgtserver.dto.project;

import com.example.mgtserver.model.Project;

/**
 * 项目统计信息DTO
 */
public class ProjectStat {
    /**
     * osgb数量
     */
    private Long osgbCount;
    /**
     * kml数量
     */
    private Long kmlLayerCount;
    /**
     * 人工建模数量
     */
    private Long artificialLayerCount;
    /**
     * 全景图数量
     */
    private Long panoramaCount;

    public ProjectStat() {
    }

    public ProjectStat(Long osgbCount, Long kmlLayerCount, Long artificialLayerCount, Long panoramaCount) {
        this.osgbCount = osgbCount;
        this.kmlLayerCount = kmlLayerCount;
        this.artificialLayerCount = artificialLayerCount;
        this.panoramaCount = panoramaCount;
    }

    public Long getOsgbCount() {
        return osgbCount;
    }

    public void setOsgbCount(Long osgbCount) {
        this.osgbCount = osgbCount;
    }

    public Long getKmlLayerCount() {
        return kmlLayerCount;
    }

    public void setKmlLayerCount(Long kmlLayerCount) {
        this.kmlLayerCount = kmlLayerCount;
    }

    public Long getArtificialLayerCount() {
        return artificialLayerCount;
    }

    public void setArtificialLayerCount(Long artificialLayerCount) {
        this.artificialLayerCount = artificialLayerCount;
    }

    public Long getPanoramaCount() {
        return panoramaCount;
    }

    public void setPanoramaCount(Long panoramaCount) {
        this.panoramaCount = panoramaCount;
    }

    @Override
    public String toString() {
        return "ProjectStat{" +
                "osgbNum=" + osgbCount +
                ", kmlLayerNum=" + kmlLayerCount +
                ", artificialLayerNum=" + artificialLayerCount +
                ", panoramaNum=" + panoramaCount +
                '}';
    }
}
