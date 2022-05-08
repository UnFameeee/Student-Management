<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="https://firebasestorage.googleapis.com/v0/b/hoaiphong-4cfd9.appspot.com/o/logo.jpg?alt=media&token=848e1981-5300-4bfc-807a-53b0b1ecc706" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Trường đại học sư phạm kĩ thuật TPHCM</h3>
  <p align="center">
     Cloud Computing
    <br />
    <a href=""><strong>Explore the docs »</strong></a>
    <br />
    <br />
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">Đôi lời về Project</a>
      <ul>
        <li><a href="#built-with">Công nghệ của Project</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Bắt đầu</a>
      <ul>
        <li><a href="#installation">Cài đặt cần thiết</a></li>
      </ul>
    </li>
    <li><a href="#usage">Sử dụng</a></li>
    <li><a href="#contributing">Đóng góp</a></li>
    <li><a href="#contact">Liên hệ</a></li>
    <li><a href="#conclusion">Kết luận</a></li>
  </ol>
</details>

## Đôi lời về Project
<!-- ![](https://drive.google.com/thumbnail?id=1mOqIo6DPXxxjRWhUb44UHPGZQI9CkjhC) -->


Đầu tiên, em xin gửi lời cảm ơn sâu sắc đến giảng viên bộ môn - thầy Huỳnh Xuân Phụng đã giúp cho nhóm em làm đồ án này, truyền đạt những hiến thức rất hay về AWS Cloud và trong thời gian giảng dạy thầy cũng đã giảng dạy rất nhiệt tình. Tham gia lớp CLoud Computing của thầy thì em đã có thêm cho mình rất nhiều kiến thức về AWS và biết đến nhiều hơn thế nào gọi là Điện Toán Đám Mây. Em xin cám ơn thầy.

Đồ án lần này nhóm thực hiện có tên là Quản lý sinh viên nhóm chúng em dùng dịch vụ EC2 của AWS để có thể tạo ra các máy ảo, sau đó dùng Docker để có thể host những phần quan trọng của một chương trình web lên: "Frontend, Backend, Database" .Ngoài ra còn triển khai thêm các dịch vụ khác như S3 Storage Bucket để có thể hỗ trợ lưu hình ảnh để có thể giúp cho phần Backend giảm tải lượng công việc.


### Công nghệ của Project
Các công nghệ, ngôn ngữ, thư viện và các dịch vụ của AWS nhóm sử dụng.
- <img src="https://drive.google.com/thumbnail?id=1pNQYDRYw-Cl5Yy1EVncaDzFxlnNeLnNq" width=50 height=50> Angular là một javascript framework do google phát triển để xây dựng các Single Page Application (SPA) bằng JavaScript , HTML và TypeScript <br>
- <img src="https://drive.google.com/thumbnail?id=1M6WjuzORiq1utf666U2riOgf04BDdthj" width=50 height=50>  Java là một một ngôn ngữ lập trình hiện đại, bậc cao, hướng đối tượng, bảo mật và mạnh mẽ. và là một Platform. Và Springboot là một nhánh trong đó cũng sử dụng Java.
- <img src="https://drive.google.com/thumbnail?id=14KA9dPr1hRoxoV1S6PyskAMenStKMnOL" width=30 height=30>  Amazon Web Services (AWS) là nền tảng đám mây toàn diện và được sử dụng rộng rãi nhất, cung cấp trên 200 dịch vụ đầy đủ tính năng từ các trung tâm dữ liệu trên toàn thế giới<br>

## Bắt đầu

### Cài đặt
Để có thể cài đặt và sử dụng dự án bạn phải cài đặt theo các yêu cầu bên dưới.

1. Tạo các máy ảo EC2 ở trên AWS
2. Cài đặt Security Group, và tạo VPC cấp cho các máy EC2 các IPv4 Public
3. Ở máy ảo EC2 Frontend: Thay đổi IP phù hợp với máy của Backend
  ```sh
  docker build -t my-fe .
  docker run -d -p 8080:80 --name my-frontend my-fe
  ```
4. Dockerfile của Frontend:
  ```sh
  FROM nginx:alpine
  COPY ./webapps/. /usr/share/nginx/html
  COPY default.conf /etc/nginx/conf.d/default.conf
  ```
![](https://firebasestorage.googleapis.com/v0/b/finalcntt.appspot.com/o/Images%2Fneed2.png?alt=media&token=c75432a1-0376-4dd9-8075-57d91349f90e)

5. Ở máy ảo EC2 Backend ( Lưu ý cần khởi động Database trước)
  ```sh
  docker build -t my-be .
  docker run -d -p 9000:80 --name my-backend -e MYSQL_HOST=52.45.238.90 -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_POST=3307 my-be
  ```
6. Dockerfile của Backend: thay đổi IP phù hợp với IP của database
  ```sh
  FROM openjdk:17
  ADD target/spring-backend.jar spring-backend.jar 
  ENTRYPOINT [ "java", "-jar", "/spring-backend.jar"]
  ```
![](https://firebasestorage.googleapis.com/v0/b/finalcntt.appspot.com/o/Images%2Fneed1.png?alt=media&token=725c3008-d255-4dc3-8a7f-6d8c4bff537c)

7. Ở máy ảo Database
  ```sh
  docker run -d -p 3307:3306 --name my-database --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=CloudProject" mysql:8.0.26
  ```

## Sử dụng
![](https://firebasestorage.googleapis.com/v0/b/finalcntt.appspot.com/o/Images%2Flogin.png?alt=media&token=39ebb76e-eae3-4d2e-9573-34e4265a31a0)
![](https://firebasestorage.googleapis.com/v0/b/finalcntt.appspot.com/o/Images%2Fdashboard.png?alt=media&token=92b16a01-9033-4979-b91b-12b76af283ca)


## Đóng góp

Những đóng góp của nhửng ai xem qua đồ án này và muốn đóng góp, hay clone dự án này về máy của bạn và sau đó thêm những ý kiến của bạn vào và sau đó push lên, create pull request và liên hệ mình qua mail: quocthang036@gmail.com để mình có thể reply và check pull request của bạn 1 cách nhanh nhất. 

Vì vậy nếu bạn có đề xuất gì để cho dự án này tốt hơn, clone về, tạo pull request và mail cho mình.

## Liên hệ
Để có thể liên hệ với mình khi gặp khó khăn mình sẽ để thông tin tại đây - 
Gmail:
```js
   quocthang036@gmail.com
```
Project Link: [https://github.com/Tien01els/CloudProject.git](https://github.com/your_username/repo_name)
