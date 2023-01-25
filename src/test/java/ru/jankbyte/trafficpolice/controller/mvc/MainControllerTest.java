package ru.jankbyte.trafficpolice.controller.mvc;

import ru.jankbyte.trafficpolice.Main;

import org.mockito.Mockito;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.http.MediaType;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnLoginPage() throws Exception {
        var mvcAction = mockMvc.perform(
            MockMvcRequestBuilders.get("/login"));
        setSuccessParams(mvcAction);
        var content = mvcAction.andExpect(
            MockMvcResultMatchers.view().name("login")).andReturn();
        var html = content.getResponse().getContentAsString();
        assertThat(html).contains("Авторизация", "username", "password");
    }

    @Test
    @WithMockUser(roles = "ROOT")
    public void shouldReturnIndexForRoot() throws Exception {
        var mvcAction = mockMvc.perform(
            MockMvcRequestBuilders.get("/"));
        setSuccessParams(mvcAction);
        var content = mvcAction.andExpect(
            MockMvcResultMatchers.view().name("index")).andReturn();
        var html = content.getResponse().getContentAsString();
        assertThat(html).contains("Панель управление", "Администратор", "Сотрудник");
    }

    @Sql(value = {"/script/drop.sql",
            "/script/schema.sql",
            "/script/data.sql"},
        executionPhase =
            Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    @WithMockUser(roles = "EDITOR", username = "editor")
    public void shouldReturnMePageForEditorRole() throws Exception {
        var mvcAction = mockMvc.perform(
            MockMvcRequestBuilders.get("/me"));
        setSuccessParams(mvcAction);
        var content = mvcAction.andExpect(
            MockMvcResultMatchers.view().name("user"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        var html = content.getResponse().getContentAsString();
        assertThat(html).contains("EDITOR", "editor", "<span>true</span>");
    }

    private void setSuccessParams(ResultActions actions) throws Exception {
        actions.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.header()
                .string("Content-Type", "text/html;charset=UTF-8"));
    }
}
