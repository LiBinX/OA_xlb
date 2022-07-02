package com.xl.oa.common.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 * 
 * @author 毕业设计
 */
@Component
@ConfigurationProperties(prefix = "oa")
public class Global
{
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;

    /** 实例演示开关 */
    private static boolean demoEnabled;

    /** 上传路径 */
    private static String profile;

    /**素材上传图片路径*/
    private static String materialFilePath;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        Global.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        Global.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        Global.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        Global.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        Global.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        Global.addressEnabled = addressEnabled;
    }

    public static String getMaterialFilePath() {
        return materialFilePath;
    }

    public static void setMaterialFilePath(String materialFilePath) {
        Global.materialFilePath = materialFilePath;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }

    /**
     * 获取图片上传路径
     */
    public static String getMaterialImgPath()
    {
        return getProfile() + "/upload/img";
    }
    /**
     * 获取缩略图片上传路径
     */
    public static String getThumbnail()
    {
        return getProfile() + "/upload/thumbnail";
    }
    /**
     * 获取视频上传路径
     */
    public static String getMaterialVideoPath()
    {
        return getProfile() + "/upload/video";
    }

    /**
     * 获取PPT WORD EXCEL上传路径
     */
    public static String getOtherFilePath()
    {
        return getProfile() + "/upload/other";
    }

    /**
     * 获取PPT WORD EXCEL上传路径
     */
    public static String getVideoImagePath()
    {
        return getProfile() + "/upload/videoImg/";
    }
}
