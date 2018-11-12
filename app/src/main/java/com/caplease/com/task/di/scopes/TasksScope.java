package com.caplease.com.task.di.scopes;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface TasksScope {
}
