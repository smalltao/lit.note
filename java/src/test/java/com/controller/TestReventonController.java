package com.controller;

import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestReventonController {

    private MockMvc mockMvc;

    private static MvcResult result;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ReventonController reventonController = new ReventonController();
        mockMvc = MockMvcBuilders.standaloneSetup(reventonController).build();
    }

    @Test
    public void test1() throws Exception {
        result = mockMvc.perform(MockMvcRequestBuilders.post("/reventondc.xml")
                .param("jsonData", ""))
                .andExpect(MockMvcResultMatchers.model().attributeExists("httpResult"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Map<String, Object> li = result.getModelAndView().getModel();
        for (String s : li.keySet()) {
            if ("httpResult".equals(s)) {
                System.out.printf("结果：【 %s 】\n", JSONObject.fromObject(li.get(s)));
            }
        }

    }

    @After
    public void setDown() throws Exception {

    }

}
