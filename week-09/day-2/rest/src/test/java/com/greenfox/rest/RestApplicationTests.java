package com.greenfox.rest;

import com.greenfox.rest.controller.GreetController;
import com.greenfox.rest.model.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.rest.RestApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class RestApplicationTests {

	GreetController greetController = new GreetController();

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void doublingTestSuccessful() throws Exception {
		mockMvc.perform(get("/doubling?input=5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is(10)));
	}

	@Test
	public void doublingTestFailed() throws Exception {
		mockMvc.perform(get("/doubling")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.error", is("Please provide a new input")));
	}

	@Test
	public void greetTestSuccessful() throws Exception {
		mockMvc.perform(get("/greeter?name=Petike&title=student")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.welcome_message", is("Oh, hi there Petike, my dear student!")));
	}

	@Test
	public void greetTestFailedForTitle() throws Exception {
		mockMvc.perform(get("/greeter?name=Petike")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.error", is("Please provide a title!")));
	}

	@Test
	public void greetTestFailedForName() throws Exception {
		mockMvc.perform(get("/greeter?title=student")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.error", is("Please provide a name!")));
	}

	@Test
	public void appendTestSuccessful() throws Exception {
		mockMvc.perform(get("/appenda/kuty")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.appended", is("kutya")));
	}

	@Test
	public void appendTestFailed() throws Exception {
		mockMvc.perform(get("/appenda")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

	@Test
	public void doUntilTestSuccessfulForSum() throws Exception {
		mockMvc.perform(post("/dountil/sum")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"until\": \"5\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is(15)));
	}

	@Test
	public void doUntilTestSuccessfulForFactorial() throws Exception {
		mockMvc.perform(post("/dountil/factor")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"until\": \"5\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is(120)));
	}

	@Test
	public void doUntilTestFailedForPageNotFound() throws Exception {
		mockMvc.perform(post("/dountil/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
				.andExpect(status().isNotFound());
	}

	@Test
	public void doUntilTestFailedForNoNumbers() throws Exception {
		mockMvc.perform(post("/dountil/sum/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"until\": \"null\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.error",is("Please provide a number!")));
	}

	@Test
	public void doUntilTestFailedForBadRequest() throws Exception {
		mockMvc.perform(post("/dountil/sum/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}