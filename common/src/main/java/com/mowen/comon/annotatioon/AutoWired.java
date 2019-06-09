package com.mowen.comon.annotatioon;

import java.lang.annotation.*;

/**
 * mowen_parent com.mowen.comon.annotatioon
 *
 * @author: mowen
 * @createTime: 2019/6/9 11:54
 * @group:
 * @description:
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWired {
}
