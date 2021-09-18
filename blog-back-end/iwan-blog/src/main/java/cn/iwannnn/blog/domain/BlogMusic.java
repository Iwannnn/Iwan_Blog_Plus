package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.iwannnn.common.annotation.Excel;
import cn.iwannnn.common.core.domain.BaseEntity;

/**
 * 博客音乐对象 blog_music
 * 
 * @author iwan
 * @date 2021-09-17
 */
public class BlogMusic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 音乐id */
    private Long musicId;

    /** 音乐名称 */
    @Excel(name = "音乐名称")
    private String name;

    /** 音乐资源 */
    @Excel(name = "音乐资源")
    private String src;

    /** 字幕文件 */
    @Excel(name = "字幕文件")
    private String lrc;

    /** 头像 */
    @Excel(name = "头像")
    private String avator;

    public void setMusicId(Long musicId) 
    {
        this.musicId = musicId;
    }

    public Long getMusicId() 
    {
        return musicId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSrc(String src) 
    {
        this.src = src;
    }

    public String getSrc() 
    {
        return src;
    }
    public void setLrc(String lrc) 
    {
        this.lrc = lrc;
    }

    public String getLrc() 
    {
        return lrc;
    }
    public void setAvator(String avator) 
    {
        this.avator = avator;
    }

    public String getAvator() 
    {
        return avator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("musicId", getMusicId())
            .append("name", getName())
            .append("src", getSrc())
            .append("lrc", getLrc())
            .append("avator", getAvator())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
