Buatlah aplikasi android dengan kriteria berikut :

1.NetCache 
Kerjakan aplikasi ini di folder NetCache

Specs :
1. Mengunduh data dari http://jsonplaceholder.typicode.com/posts
2. Data yang berhasil diunduh ditampilkan dalam bentuk searchable listview, diurutkan berdasarkan "title"
3. Data yang ditampilkan dalam tiap cell adalah "title" dari tiap JSON Object
4. Ketika item pada listview di-tap, maka akan menampilkan halaman detail berisi "title" dan "body" dari content
5. Sebagai user, saya bisa melakukan filter pada konten listview melalui input texfield berdasar "title"
6. Sebagai user, ketika saya membuka aplikasi dalam keadaan offline, saya masih bisa melihat data sebelumnya.
   Atau kosong jika tidak ada data sebelumnya, dan tampilkan tulisan "Please pull to refresh".
7. Sebagai user, saya bisa melakukan "pull to refresh" untuk melakukan load ulang
8. Sebagai user, ketika aplikasi sedang mengunduh data, saya bisa bisa melihat tampilan loading view/spinner pada halaman listview


2. FormTastic
Kerjakan aplikasi ini di folder FormTastic
Deskripsi :
Buatlah sebuah aplikasi yang menampilkan form dengan desain seperti file "form.png".
Kerahkan kemampuan terbaik Anda untuk membuat tampilan form semirip mungkin dengan gambar tersebut. (Boleh menggunakan 3rd party view library dari luar)
* Nama depan : (harus diisi, alfabet 2-10 karakter)
* Nama belakang : (harus diisi, alfabet 2-20 karakter)
* Alamat email : (harus diisi, dan harus valid email)
* Jenis kelamin : (picker view, "Laki-laki" & "Perempuan". Default "Laki-laki")
* Buat password : (harus diisi, 6-10 karakter alphanumerik)
* Confirm password : harus diisi dan sesuai dengan input password sebelumnya
* Daftar button : Hanya enabled ketika checkbox agreement dicentang

Test case :
1. Dalam keadaan agreement checkbox tidak dicentang, tombol "Daftar" tidak dapat digunakan
2. Dalam keadaan agreement checkbox dicentang, ketika tombol "Daftar" di-tap :
   * Jika input form tidak valid, munculkan Alert/PopUp/Toast (pilih salah satu) berisi informasi error input yang terjadi
   * Jika input form valid, munculkan loading view / spinner selama 2 detik, lalu pop up bertuliskan "Sukses"
3. Ketika input form tertutup keyboard, maka form akan scroll sehingga texfield kembali dapat terlihat.


===================================================================
Setelah selesai, archive parent folder "AOL__FUNDROID" dalam .zip. Pastikan project-nya sudah di-clean terlebih dahulu.
Kirim ke adi.purnama@alfacart.com (boleh attach atau cukup share link download dropbox-nya)
