package com.demo.component;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.BoolType;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.VL;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/20
 * Time: 15:55
 * Description: No Description
 */
@Erupt(name = "学生管理")        //erupt类注解
@Table(name = "t_student")    //数据库表名
@Entity                      //hibernate实体类标识
public class Student extends BaseModel {
    @EruptField(
            views = @View(title = "姓名"),
            edit = @Edit(title = "姓名")
    )
    private String name;

    @EruptField(
            views = @View(title = "性别"),
            edit = @Edit(title = "性别",
                    boolType = @BoolType(trueText = "男", falseText = "女"))
    )
    private Boolean sex;

    @EruptField(
            views = @View(title = "出生日期"),
            edit = @Edit(title = "出生日期",
                    dateType = @DateType(pickerMode = DateType.PickerMode.HISTORY) //选择历史时间
            ))
    private Date birthday;

    @EruptField(
            views = @View(title = "年级（高中）"),
            edit = @Edit(title = "年级（高中）", type = EditType.CHOICE,
                    choiceType = @ChoiceType(vl = {
                            @VL(value = "1", label = "一年级"),
                            @VL(value = "2", label = "二年级"),
                            @VL(value = "3", label = "三年级")
                    }) //支持动态获取，为了演示方便固定写了几个
            ))
    private Integer grade;
}
