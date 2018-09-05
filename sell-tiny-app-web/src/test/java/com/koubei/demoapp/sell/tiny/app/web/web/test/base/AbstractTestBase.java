package com.koubei.demoapp.sell.tiny.app.web.web.test.base;

import com.koubei.demoapp.sell.tiny.app.web.CavalrySpringBootApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 参考文档: http://docs.spring.io/spring-boot/docs/1.4.2.RELEASE/reference/htmlsingle/#boot-features-testing
 * <p>
 * <p>
 * <p/>
 * Created by yangguanchao on 16/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CavalrySpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractTestBase {

    public static final String SOFA_REST_PORT = "8341";

    @Autowired
    public EmbeddedWebApplicationContext server;

    /**
     * 8080
     */
    @LocalServerPort
    public int definedPort;


    @Autowired
    public TestRestTemplate testRestTemplate;

    public String urlHttpPrefix;

    public String sofaRestHttpPrefix;

    @Before
    public void setUp() throws Exception {
        sofaRestHttpPrefix = "http://localhost:" + SOFA_REST_PORT;
        urlHttpPrefix = "http://localhost:" + definedPort;
    }

}

