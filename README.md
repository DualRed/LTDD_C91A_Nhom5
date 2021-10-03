# Chatbot on Android
### 1. Giới thiệu
- Là một chatbot tự động chạy trên HĐH android dựa trên API của Simsimi. Có chức năng trả lời những câu đối thoại mà người dùng đưa ra.
- Ứng dụng sử dụng Simsimi API dựa theo những gì mà người dùng nhập mà đưa ra những câu trả lời khác nhau một cách tự động.
### 2. Yêu cầu cấu hình
|    Ứng dụng(app)    |                 Source Code                  |
|:-------------------:|:--------------------------------------------:|
| Android 5.0 trở lên |     JDK 1.8.0 và Android SDK 11.0 trở lên    |
### 3. Mô tả kĩ thuật và nghiệp vụ
#### a. Front-end của ứng dụng
##### Ứng dụng có tổng cộng 4 layout chính và 3 layout phụ. Layout chính bao gồm:
![frontend-treeview](https://i.imgur.com/O9PH3H4.png)
- **activity_chat.xml** là layout để người dùng chat với bot của ứng dụng.
-	**activity_main.xml** là layout chính của ứng dụng, chứa logo và màn hình chào người dùng.
-	**activity_setting.xml** là layout chứa những cài đặt cho ứng dụng như Chế độ tối (Darkmode) và thay đổi ngôn ngữ của bot.
-	**activity_splash.xml** là màn hình loading đầu tiên khi người dùng nhấn vào ứng dụng.
##### Layout phụ bao gồm:
-	**receive_message_bubble.xml** là layout chứa khung tin nhắn của người nhận(ở đây là bot). Ở phần tin nhắn nhận thì sẽ có thêm avatar của bot.
-	**send_message_bubble.xml** là layout chứa khung tin nhắn của người gửi(ở đây là người dùng).
-	**toolbar.xml** là layout chứa toolbar riêng của ứng dụng. Và nó được gắn vào đầu của **activity_chat.xml** và **activity_setting.xml**. Toolbar có 3 thành phần chính là nút trái, nút phải và title của layout đó.
#### b. Back-end của ứng dụng
##### Ứng dụng có 4 file activity chính, 2 model, 1 fragment và một số thứ khác. Trong đó:
![backend-treeview](https://i.imgur.com/1Te2kg3.png)
-	**CustomAdapter** được kế thừa từ **BaseAdaper** được chỉnh sửa để xử lý phần hiển thị tin nhắn nhận và gửi. Nó được dùng để phân biệt 2 loại tin nhắn được đưa vào để thêm vào bubble chat tương ứng.
-	**HttpDataHandler** được sử dụng để kiểm tra và kết nối đến API được chính xác.
-	**ChatbotModel** và **ChatModel** dùng để đựng thông tin được API trả về và để hiển thị lên màn hình chat. **ChatbotModel** được dùng để chứa thông tin mà API gửi về, **ChatModel** dùng để chứa tin nhắn của người dùng hoặc bot để xử lí đưa bubble chat cho đúng và được nhận biết bởi isSend(là true nếu tin nhắn đó là người dùng gửi còn false thì ngược lại).
-	**ChatAvtivity**, **MainActivity**, **SettingActivity** và **SplashActitivy** dùng để xử lý hoạt động của các layout tương ứng.
-	**SettingFragment** được dùng để xử lý sự kiện trong **activity_setting.xml**.
#### c. Cơ chế hoạt động
##### Ứng dụng sử dụng Simsimi API để gửi và nhận tin nhắn gửi về khi người dùng chat trên ứng dụng. Và ứng dụng không có database nên ứng dụng sẽ không lưu lại tin nhắn mà người dùng đã nhắn.
#####	Cơ chế hoạt động chính của ứng dụng đó là chat với bot, với từng bước xử lí như sau:
- Người dùng nhập chuỗi và nhấn gửi.
- Chuỗi vừa nhập sẽ được lưu vào một **ChatModel** để đưa lên listview.
-	Do ứng dụng có thể thay đổi ngôn ngữ của bot nên sẽ có thêm 1 hàm để lấy ngôn ngữ hiện tại đang được chọn trong phần Cài đặt của ứng dụng.
-	Lấy chuỗi và ngôn ngữ hiện tại để ghép vào đường link để lấy API về, sử dụng **HttpDataHandler** để kiểm tra đường dẫn và lưu thông tin được trả về.
-	Sử dụng lớp Gson để chuyển đổi thông tin trả về vào một **ChatbotModel**.
-	Tạo một **ChatModel** để lấy tin nhắn trả về và dùng **CustomAdapter** để đưa lên listview.
-	Sau khi hoàn tất thì tin nhắn sẽ được hiện lên màn hình người dùng.
### 4. Hướng dẫn sử dụng
![tutorial](https://i.imgur.com/1IiQGLl.png)
![tutorial](https://i.imgur.com/VEkjmBD.png)
![tutorial](https://i.imgur.com/XYiPZDV.png)
### 5. Danh sách nhóm và phân công
|    MSSV    |     Họ tên      |                   Phân công                     |
|:----------:|:---------------:|:------------------------------------------------|
| 1751010071 | Lâm Bảo Linh    | Xây dựng giao diện chat, cách tương tác với API |
| 1751010058 | Vũ Văn Khiêm    | Tìm hiểu API, xây dựng model                    |
| 1751010076 | Nguyễn Văn Long | Darkmode và một số layout khác                  |
| 1751010122 | Nguyễn Sơn Rin  | Thiết kế UI, logo                               |
### 6. Lỗi
-	Vì là ứng dụng chatbot sử dụng Simsimi API không có tùy chọn lọc những câu hội thoại không phù hợp. Nên một vài trường hợp sẽ xuất hiện.
-	Hoạt ảnh chuyển đổi chế độ Darkmode chưa được tối ưu tốt.
-	Khi chuyển đổi chế độ Darkmode thì tin nhắn từ màn hình chat từ trước sẽ bị xóa.
### 7. Tài liệu kĩ thuật
- [Android Studio Tutorial - Simsimi Chat App](https://www.youtube.com/watch?v=JAdHKPGlxvQ)
- [Android Studio Tutorial - How to Create Night Mode and Light Mode | Dark Mode for Android App](https://www.youtube.com/watch?v=OYJ1WwwnvCo)
- [Changing Theme Colors in Android Studio](https://www.youtube.com/watch?v=3NOJvEegym8)
- [Implement Night Mode in your App | Custom Styles in Android Studio](https://www.youtube.com/watch?v=-qsHE3TpJqw)
- [SharedPreferences | Android Developers](https://developer.android.com/reference/android/content/SharedPreferences)
- [Làm việc với Gson trong android - Viblo](https://viblo.asia/p/lam-viec-voi-gson-trong-android-7rVRqwNJG4bP)
