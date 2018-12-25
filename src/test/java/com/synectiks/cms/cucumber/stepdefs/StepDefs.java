package com.synectiks.cms.cucumber.stepdefs;

import com.synectiks.cms.CmsApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = CmsApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
