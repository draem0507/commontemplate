## 1. What is commontemplate ##
CommonTemplate is a open source template engine,
a generic tool to generate text output: HTML, XML, Mail, Java source code, etc.
Project Documents: http://commontemplate.googlecode.com/svn/doc/zh/index.html

## 2. How to use commontemplate ##
#### (1) Standard syntax ####
```
<html>
    <body>
        $if{users != null && users.size &gt; 0}
        <table border="1">
            $for{user : users}
            <tr>
                <td>${for.index + 1}</td>
                <td>${user.name}</td>
                <td>${user.coins}</td>
            </tr>
            $end
        </table>
        $end
    </body>
</html>
```
```
package com.${project.company}.${project.name}.domain;
$if{project.framework != project.name}
import com.${project.company}.${project.framework}.domain.BaseEntity;
$end
public class ${entity.name} extends BaseEntity {
  $for{field : entity.fields}
  private ${field.type} ${field.name};

  public ${field.type} get${field.name.capitalize}() {
    return ${field.name};
  }

  public void set${field.name.capitalize}(${field.type} ${field.name}) {
    this.${field.name} = ${field.name};
  }
  $end
}  
```
#### (2) Comment syntax ####
```
<html>
    <body>
        <!--$if{users != null && users.size &gt; 0}-->
        <table border="1">
            <!--$for{user : users}-->
            <tr>
                <td><!--$output{for.index + 1}-->1<!--$end--></td>
                <td><!--$output{user.name}-->james<!--$end--></td>
                <td><!--$output{user.coins}-->2.00<!--$end--></td>
            </tr>
            <!--$end-->
        </table>
        <!--$end-->
    </body>
</html>
```
```
package com.${project.company}.${project.name}.domain;
/*$if{project.framework != project.name}*/
import com.${project.company}.${project.framework}.domain.BaseEntity;
/*$end*/
public class ${entity.name} extends BaseEntity {
  /*$for{field : entity.fields}*/
  private ${field.type} ${field.name};

  public ${field.type} get${field.name.capitalize}() {
    return ${field.name};
  }

  public void set${field.name.capitalize}(${field.type} ${field.name}) {
    this.${field.name} = ${field.name};
  }
  /*$end*/
}  
```
#### (3) Tag syntax ####
```
<html>
    <body>
        <ct:if param="users != null && users.size &gt; 0">
            <table border="1">
                <ct:for param="user : users">
                <tr>
                    <td><ct:output param="for.index + 1">1</ct:output></td>
                    <td><ct:output param="user.name">james</ct:output></td>
                    <td><ct:output param="user.coins">2.00</ct:output></td>
                </tr>
                </ct:for>
            </table>
        </ct:if>
    </body>
</html>
```
#### (4) Attribute syntax ####
```
<html>
    <body>
        <table ct:if="users != null && users.size &gt; 0" border="1">
            <tr ct:for="user : users">
                <td><span ct:output="for.index + 1">1</span></td>
                <td><span ct:output="user.name">james</span></td>
                <td><span ct:output="user.coins">2.00</span></td>
            </tr>
        </table>
    </body>
</html>
```