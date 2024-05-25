package com.aurora.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网站配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebsiteConfigVO {

    /**
     * 网站名称
     */
    private String name;

    /**
     * 网站作者昵称
     */
    private String englishName;

    /**
     * 网站作者
     */
    private String author;

    /**
     * 网站头像
     */
    private String authorAvatar;

    /**
     * 网站作者介绍
     */
    private String authorIntro;

    /**
     * 网站logo
     */
    private String logo;

    /**
     * 多语言
     */
    private Integer multiLanguage;

    /**
     * 网站公告
     */
    private String notice;

    /**
     * 网站创建时间
     */
    private String websiteCreateTime;

    /**
     * 网站备案号
     */
    private String beianNumber;

    /**
     * QQ登录
     */
    private Integer qqLogin;

    /**
     * github
     */
    private String github;

    /**
     * gitee
     */
    private String gitee;

    /**
     * qq
     */
    private String qq;

    /**
     * 微信
     */
    private String weChat;

    /**
     * 微博
     */
    private String weibo;

    /**
     * csdn
     */
    private String csdn;

    /**
     * zhihu
     */
    private String zhihu;

    /**
     * juejin
     */
    private String juejin;

    /**
     * twitter
     */
    private String twitter;

    /**
     * stackoverflow
     */
    private String stackoverflow;

    /**
     * 游客头像
     */
    private String touristAvatar;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 是否评论审核
     */
    private Integer isCommentReview;

    /**
     * 是否邮箱通知
     */
    private Integer isEmailNotice;

    /**
     * 是否打赏
     */
    private Integer isReward;

    /**
     * 微信二维码
     */
    private String weiXinQRCode;

    /**
     * 支付宝二维码
     */
    private String alipayQRCode;

    /**
     * favicon
     */
    private String favicon;

    /**
     * 网页标题
     */
    private String websiteTitle;

    /**
     * 公安部备案编号
     */
    private String gonganBeianNumber;

}
