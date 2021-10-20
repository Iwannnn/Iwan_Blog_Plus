package cn.iwannnn.blog.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeVo {
	private Long articleId;

	private Long userId;
}
