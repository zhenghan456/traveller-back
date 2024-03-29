package com.zhenghan.scenery.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="scenery_support")
public class ScenerySupportPojo {
    @TableField("sceneryid")
    private String sceneryid;
    @TableField("userid")
    private String userid;
    public String getSceneryid(){return this.sceneryid;}
}
