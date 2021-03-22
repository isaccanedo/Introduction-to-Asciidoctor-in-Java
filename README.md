## Asciidoctor

# 1. Introdução
Neste artigo, faremos uma introdução rápida sobre como usar o Asciidoctor com Java. Vamos demonstrar como gerar HTML5 ou PDF a partir de um documento AsciiDoc.

# 2. O que é AsciiDoc?
AsciiDoc é um formato de documento de texto. Ele pode ser usado para escrever documentação, livros, páginas da web, páginas de manual e muitos outros.

Por ser muito configurável, os documentos AsciiDoc podem ser convertidos em muitos outros formatos, como HTML, PDF, páginas de manual, EPUB e outros.

Como a sintaxe do AsciiDoc é bastante básica, ela se tornou muito popular com um grande suporte em vários plug-ins de navegador, plug-ins para linguagens de programação e outras ferramentas.

Para aprender mais sobre a ferramenta, sugerimos ler a documentação oficial onde você pode encontrar muitos recursos úteis para aprender a sintaxe adequada e métodos para exportar seu documento AsciiDoc para outros formatos.

# 3. O que é Asciidoctor?
Asciidoctor é um processador de texto para converter documentos AsciiDoc em HTML, PDF e outros formatos. É escrito em Ruby e empacotado como RubyGem.

Como mencionado acima, AsciiDoc é um formato muito popular para escrever documentação, então você pode facilmente encontrar o Asciidoctor como um pacote padrão em muitas distribuições GNU Linux como Ubuntu, Debian, Fedora e Arch.

Como queremos usar o Asciidoctor na JVM, vamos falar sobre AsciidoctorJ - que é Asciidoctor com Java.

# 4. Dependências
Para incluir o pacote AsciidoctorJ em nosso aplicativo, a seguinte entrada pom.xml é necessária:

```
<dependency>
    <groupId>org.asciidoctor</groupId>
    <artifactId>asciidoctorj</artifactId>
    <version>1.5.5</version>
</dependency>
<dependency>
    <groupId>org.asciidoctor</groupId>
    <artifactId>asciidoctorj-pdf</artifactId>
    <version>1.5.0-alpha.15</version>
</dependency>
```