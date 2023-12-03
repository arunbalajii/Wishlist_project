package com.walmart.wishlist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WishlistApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_ValidUserInfo() throws Exception {
        String requestBody = "{\"email\": \"arun9@gmail.com\"}";

        mockMvc.perform(post("/wishlist/fetch/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void test_InValidUserInfo() throws Exception {
        String requestBody = "{\"email\": \"arun9@gmail.co\"}";

        mockMvc.perform(post("/wishlist/fetch/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void test_ValidUserItemEntry() throws Exception {
        String requestBody = "{\n" +
                "    \"email\": \"arun9@gmail.com\",\n" +
                "    \"products\": [\n" +
                "       \n" +
                "         {\n" +
                "            \"id\": 1,\n" +
                "            \"quantity\":6\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(post("/wishlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void test_InValidUserItemEntry() throws Exception {
        String requestBody = "{\n" +
                "    \"products\": [\n" +
                "       \n" +
                "         {\n" +
                "            \"id\": 1,\n" +
                "            \"quantity\":6\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(post("/wishlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void test_ValidItemRemove() throws Exception {
        String requestBody = "{\n" +
                "    \"email\": \"arun9@gmail.com\",\n" +
                "    \"id\": \"7\"\n" +
                "}";

        mockMvc.perform(delete("/wishlist/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void test_InValidItemRemove() throws Exception {
        String requestBody = "{\n" +
                "    \"email\": \"arun9@gmail.c\",\n" +
                "    \"id\": \"7\"\n" +
                "}";

        mockMvc.perform(delete("/wishlist/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isInternalServerError());
    }
}