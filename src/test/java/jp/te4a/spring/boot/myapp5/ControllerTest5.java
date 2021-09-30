package jp.te4a.spring.boot.myapp5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class ApplicationTest5 {

	@Autowired
    MockMvc mockMvc;  
    @Autowired
    WebApplicationContext wac;  
    @BeforeAll
    public void テスト前処理() {
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	

	@Test
    public void indexに遷移しているか () throws Exception {

        MvcResult result = mockMvc.perform(get("/") )

            .andExpect(status().is2xxSuccessful())
            .andExpect(view().name("index"))
            .andReturn();
	}   

	@Test
    public void post_form_1 () throws Exception {

        MvcResult result = mockMvc.perform(  post("/post").param("text1", "aaa") )
            .andExpect(status().is2xxSuccessful())
			.andExpect(view().name("index"))
			.andReturn();
			
		ModelAndView m =  result.getModelAndView();
		assertEquals("index", m.getViewName());
		assertEquals("you wrote 'aaa'!!!", m.getModel().get("msg"));//<you wrote aaa!!>
	}   	
	
	@Test
    public void post_form_2 () throws Exception {

        MvcResult result = mockMvc.perform(post("/post").param("text1",""))
            .andExpect(status().is2xxSuccessful())
			.andExpect(view().name("index"))
			.andReturn();
			
		ModelAndView m =  result.getModelAndView();
		assertEquals("index", m.getViewName());
		assertEquals("you write ''!!!", m.getModel().get("msg"));//<you wrote !!>
    }   	

    @Test
    public void post_form_3 () throws Exception {

        MvcResult result = mockMvc.perform(post("/post").param("text1","bbb"))
            .andExpect(status().is2xxSuccessful())
			.andExpect(view().name("index"))
			.andReturn();
			
		ModelAndView m =  result.getModelAndView();
		assertEquals("index", m.getViewName());
		assertEquals("you wrote bbb!!", m.getModel().get("msg"));
    } 
    
    @Test
    public void post_form_4 () throws Exception {

        MvcResult result = mockMvc.perform(post("/post").param("text1","404"))
            .andExpect(status().is2xxSuccessful())
			.andExpect(view().name("index"))
			.andReturn();
			
		ModelAndView m =  result.getModelAndView();
		assertEquals("index", m.getViewName());
		assertEquals("you write 404!!", m.getModel().get("msg"));
    } 

    @Test
    public void post_form_5 () throws Exception {

        MvcResult result = mockMvc.perform(post("/post").param("text1","553"))
            .andExpect(status().is2xxSuccessful())
			.andExpect(view().name("index"))
			.andReturn();
			
		ModelAndView m =  result.getModelAndView();
		assertEquals("index", m.getViewName());
		assertEquals("you wrote '553'!!", m.getModel().get("msg"));
    } 

}
