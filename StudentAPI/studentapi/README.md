# Student API

Bu proje, basit bir öğrenci API'sidir. Spring Boot kullanarak oluşturulmuştur.

## Kurulum

1. Bu repository'yi klonlayın:

   ```sh
   git clone https://github.com/erenyildiz23/studentapi.git

2.Proje dizinine gidin:
cd studentapi

3.Gerekli bağımlılıkları yükleyin ve uygulamayı başlatın:

./mvnw spring-boot:run


API Endpoints

Get All Students

URL: /api/students
Method: GET
Description: Tüm öğrencileri getirir.
Get Student By ID
URL: /api/students/{id}
Method: GET
Description: Belirtilen ID'ye sahip öğrenciyi getirir.

Create Student
URL: /api/students
Method: POST
Description: Yeni bir öğrenci oluşturur.
Request Body:
{
  "name": "Ali",
  "email": "ali@example.com"
}

Update Student
URL: /api/students/{id}
Method: PUT
Description: Belirtilen ID'ye sahip öğrenciyi günceller.
Request Body:
{
  "name": "Ali Veli",
  "email": "aliveli@example.com"
}

Delete Student
URL: /api/students/{id}
Method: DELETE
Description: Belirtilen ID'ye sahip öğrenciyi siler.

Update Student Email
URL: /api/students/{id}
Method: PATCH
Description: Belirtilen ID'ye sahip öğrencinin e-posta adresini günceller.
Request Body:
{
  "email": "newemail@example.com"
}
