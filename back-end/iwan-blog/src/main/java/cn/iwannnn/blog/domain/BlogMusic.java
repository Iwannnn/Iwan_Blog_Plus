package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.iwannnn.common.annotation.Excel;
import cn.iwannnn.common.core.domain.BaseEntity;

/**
 * 博客音乐对象 blog_music
 * 
 * @author iwan
 * @date 2021-10-11
 */
public class BlogMusic extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 音乐id */
	private Long musicId;

	/** 音乐名称 */
	@Excel(name = "音乐名称")
	private String name;

	/** 歌手 */
	@Excel(name = "歌手")
	private String artist;

	/** 字幕文件 */
	@Excel(name = "字幕文件")
	private String lrc;

	/** 音乐资源 */
	@Excel(name = "音乐资源")
	private String url;

	/** 头像 */
	@Excel(name = "头像")
	private String cover;

	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}

	public Long getMusicId() {
		return musicId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	public String getLrc() {
		return lrc;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCover() {
		return cover;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("musicId", getMusicId())
				.append("name", getName()).append("artist", getArtist()).append("lrc", getLrc()).append("url", getUrl())
				.append("createTime", getCreateTime()).append("cover", getCover()).append("updateTime", getUpdateTime())
				.append("remark", getRemark()).toString();
	}
}
