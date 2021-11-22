rem @echo off
rem переход в папку бат файла - чтоб не указывать полный путь
rem cd /d %~dp0


set n=8
set File_Src=HAND_new_134.obj
set suffiks=_formatting.obj
set file_Dest=%File_Src:~0,-4%%suffiks%
 
rem set /A n+=1
rem more /+%n% < "%File_Src%" > "%file_Dest%"

cd.>"%file_Dest%"

findstr /C:" vertices" %File_Src%>>"%file_Dest%"
findstr /C:"v " %File_Src%>>"%file_Dest%"
findstr /C:" texture coordinates" %File_Src%>>"%file_Dest%"
findstr /C:"vt " %File_Src%>>"%file_Dest%"
findstr /C:" vertex normals" %File_Src%>>"%file_Dest%"
findstr /C:"vn " %File_Src%>>"%file_Dest%"
findstr /C:" facets" %File_Src%>>"%file_Dest%"
findstr /C:"f " %File_Src%>>"%file_Dest%"
rem Pause
Exit /B