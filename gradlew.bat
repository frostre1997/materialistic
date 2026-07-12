@rem Minimal gradlew.bat
@set DIR=%~dp0
@set WRAPPER_JAR=%DIR%gradlewrappergradle-wrapper.jar

@if not exist "%WRAPPER_JAR%" (
  @echo gradle-wrapper.jar not found.
  @exit /b 1
)

@java -jar "%WRAPPER_JAR%" %*
