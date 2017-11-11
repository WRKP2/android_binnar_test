# android_binnar_test
1. Desain dan tools yang digunakan

-Table Restoran 
 ID*, nama_restoran, alamat_restoran, no_tlp

-Menu
 ID*, ID_Restoran, Nama_menu, Harga_Menu, Foto_Menu

-Order
 ID*, ID_Restoran, ID_menu, ID_UserApp, jumlah_orderan, order_date, status, alamat_pengiriman

-UserApp
 ID*, nama, alamat, e-mail, password, alamat

Saya menggunakan desaign seperti itu untuk memudahkan pelacakan order. Tools menggunakan Gradle untuk membuild Android, sedangkan untuk DB menggunakan MySql dan PHP menggunakan PHP 5.  

2. Untuk keamanan dalam pengiriman data menggunakana metode REST API dengan HTTP method yang digunakan POST. Restpon web service yang diberikan ke mobile database berupa JSON Format.
   Dalam membuat nama parameter dibuat costume sesuai dengan kesepakatan antara web dev dengan mobile dev agar jika ada pihak luar mencoba coba API web service maka web dev dapat mengarahkan respon yang salah.



