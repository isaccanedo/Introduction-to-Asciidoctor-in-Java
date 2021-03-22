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

As versões mais recentes das bibliotecas podem ser encontradas aqui e aqui.

# 5. API AsciidoctorJ
O ponto de entrada para AsciidoctorJ é a interface Asciidoctor Java.

Esses métodos são:

- convert - analisa o documento AsciiDoc de uma String ou Stream e - converte para o tipo de formato fornecido;
- convertFile - analisa o documento AsciiDoc de um objeto Arquivo fornecido e o converte para o tipo de formato fornecido;
- convertFiles - igual ao anterior, mas o método aceita vários objetos File;
- convertDirectory - analisa todos os documentos AsciiDoc na pasta fornecida e os converte para o tipo de formato fornecido

### 5.1. Uso da API no código
Para criar uma instância Asciidoctor, você precisa recuperar a instância do método de fábrica fornecido:

```
import static org.asciidoctor.Asciidoctor.Factory.create;
import org.asciidoctor.Asciidoctor;
..
//some code
..
Asciidoctor asciidoctor = create();
```

Com a instância recuperada, podemos converter o documento AsciiDoc muito facilmente:

```
String output = asciidoctor
  .convert("Hello _Baeldung_!", new HashMap<String, Object>());
```

If we want to convert a text document from the file system, we'll use the convertFile method:

```
String output = asciidoctor
  .convertFile(new File("baeldung.adoc"), new HashMap<String, Object>());
```

Para converter vários arquivos, o método convertFiles aceita o objeto List como um primeiro parâmetro e retorna matrizes de objetos String.
Mais interessante é como converter um diretório inteiro com AsciidoctorJ.

Conforme mencionado acima, para converter um diretório inteiro - devemos chamar o método convertDirectory. Isso verifica o caminho fornecido e procura todos os arquivos com extensões AsciiDoc (.adoc, .ad, .asciidoc, .asc) e os converte. Para verificar todos os arquivos, uma instância do DirectoryWalker deve ser fornecida ao método.

Atualmente, o Asciidoctor fornece duas implementações integradas da interface mencionada:

- AsciiDocDirectoryWalker - converte todos os arquivos de determinada pasta e suas subpastas. Ignora todos os arquivos que começam com “_”;
- GlobDirectoryWalker - converte todos os arquivos de determinada pasta seguindo uma expressão glob.

```
String[] result = asciidoctor.convertDirectory(
  new AsciiDocDirectoryWalker("src/asciidoc"),
  new HashMap<String, Object>());
```

Além disso, podemos chamar o método de conversão com as interfaces java.io.Reader e java.io.Writer fornecidas. A interface do leitor é usada como fonte e a interface do gravador é usada para gravar os dados convertidos:

```
FileReader reader = new FileReader(new File("sample.adoc"));
StringWriter writer = new StringWriter();
 
asciidoctor.convert(reader, writer, options().asMap());
 
StringBuffer htmlBuffer = writer.getBuffer();
```

### 5.2 Geração de PDF
Para gerar um arquivo PDF a partir de um documento Asciidoc, precisamos especificar o tipo do arquivo gerado nas opções. Se você olhar um pouco mais cuidadosamente para os exemplos anteriores, você notará que o segundo parâmetro de qualquer método convert é um Mapa - que representa o objeto de opções.

Definiremos a opção in_place como true para que nosso arquivo seja gerado automaticamente e salvo no sistema de arquivos:

```
Map<String, Object> options = options()
  .inPlace(true)
  .backend("pdf")
  .asMap();

String outfile = asciidoctor.convertFile(new File("baeldung.adoc"), options);
```

# 6. Plug-in Maven
Na seção anterior, mostramos como podemos gerar arquivos PDF diretamente com sua própria implementação em Java. Nesta seção, mostraremos como gerar um arquivo PDF durante a construção do Maven. Existem plug-ins semelhantes para Gradle e Ant.

Para habilitar a geração de PDF durante a construção, você precisa adicionar esta dependência ao seu pom.xml:

```
<plugin>
    <groupId>org.asciidoctor</groupId>
    <artifactId>asciidoctor-maven-plugin</artifactId>
    <version>1.5.5</version>
    <dependencies>
        <dependency>
            <groupId>org.asciidoctor</groupId>
            <artifactId>asciidoctorj-pdf</artifactId>
            <version>1.5.0-alpha.15</version>
        </dependency>
    </dependencies>
</plugin>
```

A versão mais recente da dependência do plugin Maven pode ser encontrada aqui.

### 6.1. Uso
Para usar o plugin na construção, você deve defini-lo no pom.xml:

```
<plugin>
    <executions>
        <execution>
            <id>output-html</id> 
            <phase>generate-resources</phase> 
            <goals>
                <goal>process-asciidoc</goal> 
            </goals>
        </execution>
    </executions>
</plugin>
```

Como o plugin não é executado em nenhuma fase específica, você deve definir a fase em que deseja iniciá-lo.

Tal como acontece com o plugin Asciidoctorj, podemos usar várias opções para geração de PDF aqui também.

Vamos dar uma olhada rápida nas opções básicas enquanto você encontra outras opções na documentação:

- sourceDirectory - a localização do diretório onde você tem documentos Asciidoc;
- outputDirectory - a localização do diretório onde você deseja armazenar os arquivos PDF gerados;
- backend - o tipo de saída do Asciidoctor. Para geração de PDF definido para PDF.
Este é um exemplo de como definir opções básicas no plugin:

```
<plugin>
    <configuration>
        <sourceDirectory>src/main/doc</sourceDirectory>
        <outputDirectory>target/docs</outputDirectory>
        <backend>pdf</backend>
    </configuration>
</plugin>
```

Depois de executar a construção, os arquivos PDF podem ser encontrados no diretório de saída especificado.

# 7. Conclusão
Embora o AsciiDoc seja muito fácil de usar e entender, é uma ferramenta muito poderosa para gerenciar documentação e outros documentos.

Neste artigo, demonstramos uma maneira simples de gerar arquivos HTML e PDF a partir de um documento AsciiDoc.