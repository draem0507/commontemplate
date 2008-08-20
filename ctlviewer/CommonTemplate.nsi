; 该脚本使用 HM VNISEdit 脚本编辑器向导产生

; 安装程序初始定义常量
!define PRODUCT_NAME "CommonTemplate"
!define PRODUCT_VERSION "1.0"
!define PRODUCT_PUBLISHER "http://www.commontemplate.org"
!define PRODUCT_WEB_SITE "http://www.commontemplate.org"
!define PRODUCT_DIR_REGKEY "Software\Microsoft\Windows\CurrentVersion\App Paths\CommonTemplate.exe"
!define PRODUCT_UNINST_KEY "Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_UNINST_ROOT_KEY "HKLM"

SetCompressor lzma

; ------ MUI 现代界面定义 (1.67 版本以上兼容) ------
!include "MUI.nsh"

; MUI 预定义常量
!define MUI_ABORTWARNING
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install.ico"
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall.ico"

; 语言选择窗口常量设置
!define MUI_LANGDLL_REGISTRY_ROOT "${PRODUCT_UNINST_ROOT_KEY}"
!define MUI_LANGDLL_REGISTRY_KEY "${PRODUCT_UNINST_KEY}"
!define MUI_LANGDLL_REGISTRY_VALUENAME "NSIS:Language"

; 欢迎页面
!insertmacro MUI_PAGE_WELCOME
; 许可协议页面
!insertmacro MUI_PAGE_LICENSE "license.txt"
; 安装目录选择页面
!insertmacro MUI_PAGE_DIRECTORY
; 安装过程页面
!insertmacro MUI_PAGE_INSTFILES
; 安装完成页面
!define MUI_FINISHPAGE_RUN "$INSTDIR\CommonTemplate.exe"
!insertmacro MUI_PAGE_FINISH

; 安装卸载过程页面
!insertmacro MUI_UNPAGE_INSTFILES

; 安装界面包含的语言设置
!insertmacro MUI_LANGUAGE "English"
!insertmacro MUI_LANGUAGE "SimpChinese"

; 安装预释放文件
!insertmacro MUI_RESERVEFILE_LANGDLL
!insertmacro MUI_RESERVEFILE_INSTALLOPTIONS
; ------ MUI 现代界面定义结束 ------

Name "${PRODUCT_NAME} ${PRODUCT_VERSION}"
OutFile "CommonTemplateSetup-SNAPSHOT.exe"
InstallDir "$PROGRAMFILES\CommonTemplate"
InstallDirRegKey HKLM "${PRODUCT_UNINST_KEY}" "UninstallString"
ShowInstDetails show
ShowUnInstDetails show
BrandingText " "

Section "MainSection" SEC01
  SetOutPath "$INSTDIR"
  SetOverwrite on
  File "CommonTemplate.exe"
  CreateDirectory "$SMPROGRAMS\CommonTemplate"
  CreateShortCut "$SMPROGRAMS\CommonTemplate\CommonTemplate.lnk" "$INSTDIR\CommonTemplate.exe"
  CreateShortCut "$DESKTOP\CommonTemplate.lnk" "$INSTDIR\CommonTemplate.exe"
SectionEnd

Section -AdditionalIcons
  WriteIniStr "$INSTDIR\${PRODUCT_NAME}.url" "InternetShortcut" "URL" "${PRODUCT_WEB_SITE}"
  CreateShortCut "$SMPROGRAMS\CommonTemplate\www.commontemplate.org.lnk" "$INSTDIR\${PRODUCT_NAME}.url"
  CreateShortCut "$SMPROGRAMS\CommonTemplate\Uninstall.lnk" "$INSTDIR\uninst.exe"
SectionEnd

Section -Post
  WriteUninstaller "$INSTDIR\uninst.exe"
  WriteRegStr HKLM "${PRODUCT_DIR_REGKEY}" "" "$INSTDIR\CommonTemplate.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayName" "$(^Name)"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "UninstallString" "$INSTDIR\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayIcon" "$INSTDIR\CommonTemplate.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayVersion" "${PRODUCT_VERSION}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "URLInfoAbout" "${PRODUCT_WEB_SITE}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "Publisher" "${PRODUCT_PUBLISHER}"
  DeleteRegKey HKCR ".ctl"
  DeleteRegKey HKCR "ctlfile"
  WriteRegStr HKCR "ctlfile\DefaultIcon" "" "$INSTDIR\CommonTemplate.exe"
  WriteRegStr HKCR "ctlfile\shell\open\command" "" "$INSTDIR\CommonTemplate.exe %1"
  WriteRegStr HKCR "ctlfile\shell\0edit" "" "Edit"
  WriteRegStr HKCR "ctlfile\shell\0edit\command" "" "$WINDIR\notepad.exe %1"
  WriteRegStr HKCR "ctlfile\shell\1view" "" "CommonTemplate(view)"
  WriteRegStr HKCR "ctlfile\shell\1view\command" "" "$INSTDIR\CommonTemplate.exe %1"
  WriteRegStr HKCR "ctlfile\shell\2debug" "" "CommonTemplate(debug)"
  WriteRegStr HKCR "ctlfile\shell\2debug\command" "" "$INSTDIR\CommonTemplate.exe d %1"
  WriteRegStr HKCR "ctlfile\shell\3generate" "" "CommonTemplate(generate)"
  WriteRegStr HKCR "ctlfile\shell\3generate\command" "" "$INSTDIR\CommonTemplate.exe g %1"
  WriteRegStr HKCR ".ctl" "" "ctlfile"
SectionEnd

#-- 根据 NSIS 脚本编辑规则，所有 Function 区段必须放置在 Section 区段之后编写，以避免安装程序出现未可预知的问题。--#

Function .onInit
  !insertmacro MUI_LANGDLL_DISPLAY
FunctionEnd

/******************************
 *  以下是安装程序的卸载部分  *
 ******************************/

Section Uninstall
  Delete "$INSTDIR\${PRODUCT_NAME}.url"
  Delete "$INSTDIR\uninst.exe"
  Delete "$INSTDIR\CommonTemplate.exe"

  Delete "$SMPROGRAMS\CommonTemplate\Uninstall.lnk"
  Delete "$SMPROGRAMS\CommonTemplate\www.commontemplate.org.lnk"
  Delete "$DESKTOP\CommonTemplate.lnk"
  Delete "$SMPROGRAMS\CommonTemplate\CommonTemplate.lnk"

  RMDir "$SMPROGRAMS\CommonTemplate"

  RMDir "$INSTDIR"

  DeleteRegKey ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}"
  DeleteRegKey HKLM "${PRODUCT_DIR_REGKEY}"
  DeleteRegKey HKCR ".ctl"
  DeleteRegKey HKCR "ctlfile"
  SetAutoClose true
SectionEnd

#-- 根据 NSIS 脚本编辑规则，所有 Function 区段必须放置在 Section 区段之后编写，以避免安装程序出现未可预知的问题。--#

Function un.onInit
!insertmacro MUI_UNGETLANGUAGE
  MessageBox MB_ICONQUESTION|MB_YESNO|MB_DEFBUTTON2 "你确实要完全移除 CommonTemplate ，及其所有的组件？" IDYES +2
  Abort
FunctionEnd

Function un.onUninstSuccess
  HideWindow
  MessageBox MB_ICONINFORMATION|MB_OK "CommonTemplate 已成功地从你的计算机移除。"
FunctionEnd
