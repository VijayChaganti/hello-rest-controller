package com.galvanize;

import com.galvanize.controllers.HelloRestController;
import com.galvanize.entities.Person;
import org.json.JSONString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.galvanize.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloRestControllerTest {

    @Autowired
    MockMvc mvc;
    @Test
    public void helloRegistration(){
        HelloRestController hrc = new HelloRestController();
        Person p = hrc.sayHello("rob", getTestDob(10),"vij.chag@gmail.com", "address1");
        assertEquals(p.getAge(), 10);
    }

    //Method to create a date in the past for testing
    private Date getTestDob(int years){
        LocalDate ld = LocalDate.now();
        ld.minusYears(1l);

        Calendar ci = Calendar.getInstance();
        ci.add(Calendar.YEAR, -years);
        return ci.getTime();
    }

    @Test
    void helloRegGetReturnsPerson() throws Exception {
        String url = "/hello?name=vijay&birthDate=11/16/1991&email=vij.chag@gmail.com&address=address1";
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("vij.chag@gmail.com")))
                .andExpect(jsonPath("$.age").value(28));
    }

  @Test
    public void postRestControllerTest() throws Exception {

        String json = "{\"name\":\"vijay\",\"birthDate\":\"11/01/1990\",\"email\":\"vij.chag@gmail.com\",\"address\":\"address1\"}";

        mvc.perform(post("/hello")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("vij.chag@gmail.com")))
                .andExpect(jsonPath("$.age").value(29));

  }
}
