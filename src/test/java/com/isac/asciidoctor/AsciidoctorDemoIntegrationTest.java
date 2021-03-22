package com.isac.asciidoctor;

import org.junit.Assert;
import org.junit.Test;

public class AsciidoctorDemoIntegrationTest {

    @Test
    public void givenString_whenConverting_thenResultingHTMLCode() {
        final AsciidoctorDemo asciidoctorDemo = new AsciidoctorDemo();
        Assert.assertEquals(asciidoctorDemo.generateHTMLFromString("Hello _Isac_!"), "<div class=\"paragraph\">\n<p>Hello <em>Isac</em>!</p>\n</div>");
    }
}
