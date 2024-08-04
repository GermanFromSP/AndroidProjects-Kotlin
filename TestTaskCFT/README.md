# Test task for CFT (Center for Financial Technologies)
This test application should be able to receive data and present it in the form of a user business card. The data is taken from this API: https://randomuser.me/

The main requirements for the task were as follows:

- handling connection errors
- the ability to update the list of users
- detailed user card
- caching the last loaded list
- the ability to go to the corresponding applications by clicking on the following card fields: phone numbers, email, coordinates, address.

All tasks were completed, the following technology stack was used:
- Kotlin Coroutines
- Retrofit
- Dagger 2
- Picasso
- Room

## User list demonstration:

| User list | Update user list |
| ----------- | ---------- |
| ![user_list](https://github.com/user-attachments/assets/4237e871-fe42-457b-a621-446182040287) | ![update_user_list](https://github.com/user-attachments/assets/6cd46162-852c-4fa4-a0cd-d19aba9af1be)
|

## User details demonstration:

| Clicks on communication links | Clicks on address links |
| --------------- | --------------- |
| ![click_phone](https://github.com/user-attachments/assets/ccd71e4a-b191-432d-a0db-0c6c67efd17a)| ![click_address](https://github.com/user-attachments/assets/dd08974e-a929-46f7-b827-9cb469e30819) |



