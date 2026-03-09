package com.anax.devops.infrastructure.input.rest;

import com.anax.devops.domain.service.DevOpsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // NUEVO IMPORT
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DevOpsController.class)
public class DevOpsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DevOpsService devOpsService;

    private final String API_KEY = "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c";

    @Test
    void testHandlePost_Success() throws Exception {
        when(devOpsService.processMessage(any())).thenReturn("Hello Juan Perez your message will be send");

        String json = "{\"message\":\"test\",\"to\":\"Juan Perez\",\"from\":\"Rita\",\"timeToLifeSec\":45}";

        mockMvc.perform(post("/DevOps")
                        .header("X-Parse-REST-API-Key", API_KEY)
                        .header("X-JWT-KWY", "jwt1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testHandlePost_BadRequest() throws Exception {

        String json = "bad json";

        mockMvc.perform(post("/DevOps")
                        .header("X-Parse-REST-API-Key", API_KEY)
                        .header("X-JWT-KWY", "jwt1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testHandlePost_InvalidApiKey() throws Exception {
        String json = "{\"message\":\"test\",\"to\":\"Juan Perez\",\"from\":\"Rita\",\"timeToLifeSec\":45}";

        mockMvc.perform(post("/DevOps")
                        .header("X-Parse-REST-API-Key", "invalid-key")
                        .header("X-JWT-KWY", "jwt2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }


}