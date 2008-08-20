; �ýű�ʹ�� HM VNISEdit �ű��༭���򵼲���

; ��װ�����ʼ���峣��
!define PRODUCT_NAME "CommonTemplate"
!define PRODUCT_VERSION "1.0"
!define PRODUCT_PUBLISHER "http://www.commontemplate.org"
!define PRODUCT_WEB_SITE "http://www.commontemplate.org"
!define PRODUCT_DIR_REGKEY "Software\Microsoft\Windows\CurrentVersion\App Paths\CommonTemplate.exe"
!define PRODUCT_UNINST_KEY "Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_UNINST_ROOT_KEY "HKLM"

SetCompressor lzma

; ------ MUI �ִ����涨�� (1.67 �汾���ϼ���) ------
!include "MUI.nsh"

; MUI Ԥ���峣��
!define MUI_ABORTWARNING
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install.ico"
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall.ico"

; ����ѡ�񴰿ڳ�������
!define MUI_LANGDLL_REGISTRY_ROOT "${PRODUCT_UNINST_ROOT_KEY}"
!define MUI_LANGDLL_REGISTRY_KEY "${PRODUCT_UNINST_KEY}"
!define MUI_LANGDLL_REGISTRY_VALUENAME "NSIS:Language"

; ��ӭҳ��
!insertmacro MUI_PAGE_WELCOME
; ���Э��ҳ��
!insertmacro MUI_PAGE_LICENSE "license.txt"
; ��װĿ¼ѡ��ҳ��
!insertmacro MUI_PAGE_DIRECTORY
; ��װ����ҳ��
!insertmacro MUI_PAGE_INSTFILES
; ��װ���ҳ��
!define MUI_FINISHPAGE_RUN "$INSTDIR\CommonTemplate.exe"
!insertmacro MUI_PAGE_FINISH

; ��װж�ع���ҳ��
!insertmacro MUI_UNPAGE_INSTFILES

; ��װ�����������������
!insertmacro MUI_LANGUAGE "English"
!insertmacro MUI_LANGUAGE "SimpChinese"

; ��װԤ�ͷ��ļ�
!insertmacro MUI_RESERVEFILE_LANGDLL
!insertmacro MUI_RESERVEFILE_INSTALLOPTIONS
; ------ MUI �ִ����涨����� ------

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

#-- ���� NSIS �ű��༭�������� Function ���α�������� Section ����֮���д���Ա��ⰲװ�������δ��Ԥ֪�����⡣--#

Function .onInit
  !insertmacro MUI_LANGDLL_DISPLAY
FunctionEnd

/******************************
 *  �����ǰ�װ�����ж�ز���  *
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

#-- ���� NSIS �ű��༭�������� Function ���α�������� Section ����֮���д���Ա��ⰲװ�������δ��Ԥ֪�����⡣--#

Function un.onInit
!insertmacro MUI_UNGETLANGUAGE
  MessageBox MB_ICONQUESTION|MB_YESNO|MB_DEFBUTTON2 "��ȷʵҪ��ȫ�Ƴ� CommonTemplate ���������е������" IDYES +2
  Abort
FunctionEnd

Function un.onUninstSuccess
  HideWindow
  MessageBox MB_ICONINFORMATION|MB_OK "CommonTemplate �ѳɹ��ش���ļ�����Ƴ���"
FunctionEnd
