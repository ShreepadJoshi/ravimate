<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML//EN">
<html>
<!-- This launcher works fine with Explorer (with Javascript or without) as
     well as with Mozilla on Windows -->
<head>
  <title>Mind Map</title>
  
  <!--   ^ Put the name of your mind map here -->
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

  <APPLET CODE="freemind.main.FreeMindApplet.class" ARCHIVE="../mapjar/freemindbrowser.jar"  WIDTH="100%" HEIGHT="100%">
  <PARAM NAME="type" VALUE="application/x-java-applet;version=1.4">
  <PARAM NAME="scriptable" VALUE="false">
  <PARAM NAME="modes" VALUE="freemind.modes.browsemode.BrowseMode">
  <PARAM NAME="browsemode_initial_map"
         VALUE="http://localhost:8080<%=request.getParameter("mmfilepath") %>">
  <!--          ^ Put the path to your map here  -->
<param NAME="initial_mode" VALUE="Browse">
<param NAME="selection_method" VALUE="selection_method_direct">
</APPLET>
</body>
</html>


