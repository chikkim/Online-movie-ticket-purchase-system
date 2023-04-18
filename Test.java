public class Test {

    public static void testBlockingUser() {

        //create a fake user
        User user = new User("testBlocking", "1");
        user.authenticateUser("999", "fakeemail@fake.come");

        //block user by setting his status to "blocked"
        user.setStatus("blocked");
        
        //check status to see we are successful or not
        if (user.getStatus().equals("blocked")) {
            System.out.println("Block user successfully! \n");
        }
        else {
            System.out.println("Blocking user fails! \n");
        }
    }

    public static void testUserSignup() {
        User user = new User("testSignUpUser", "1");
        user.authenticateUser("743", "fakeemail@fake.come");
        if(user.checkUserExists()){
            System.out.println("Success: A new user could sign up");
        } else {
            System.out.println("Failure: A new user could NOT sign up");
        }
    }

    public static void testGettingUserIDWorks() {
        User user = new User(1);
        if (user.getId() == 1) {
            System.out.println("Success: Correct user id is retrieved");
        } else {
            System.out.println("Failure: Correct user id is NOT retrieved");
        }
    }

    public static void testUserStatusIsAccurateForActiveUser() {
        User user = new User("testing", "newPassword");
        if (user.getStatus().equals("active")){
            System.out.println("Success: The correct 'active' tag is associated with the user");
        } else {
            System.out.println("Failure: The correct 'active' tag is NOT associated with the user");
        }
    }

    public static void testUserStatusIsAccurateForBlockedUser() {
        User user = new User("testBlocking", "newPassword");
        if (user.getStatus().equals("blocked")){
            System.out.println("Success: The correct 'blocked' tag is associated with the user");
        } else {
            System.out.println("Failure: The correct 'blocked' tag is NOT associated with the user");
        }
    }

    public static void testChangeUserPasswordIsSuccessful() {
        User user = new User("testing", "newPassword");
        user.changePassword("testPass");
        if (user.getUserPassword().equals("testPass")) {
            System.out.println("Success: User password was able to be updated");
        } else {
            System.out.println("Failure: User password was NOT able to be updated");
        }

        //clean up
        user.changePassword("newPassword");
    }

    public static void testUserPurchasedMoviesAreAccurate() {
        User user = new User("abc", "123");
        if (user.getPurchase().equals("abc,LOTR::Oedon Cinemas 180 11::2,LOTR::Oedon Cinemas 180 11::1")){
            System.out.println("Success: User's expected purchased tickets are shown");
        } else {
            System.out.println("Success: User password was able to be updated");
        }
    }

    public static void testGettingUserNameWorks() {
        User user = new User("testBlocking", "1");
        if(user.getUsername().equals("testBlocking")){
            System.out.println("Was able to get username \n");
        } else {
            System.out.println("Was NOT able to get username \n");
        }
    }

    public static void testValidUserLogin() {
        //Given
        User user = new User("test", "user");
        if(user.checkUserExists()){
            System.out.println("Valid User can login! \n");
        } else {
            System.out.println("Valid User wasn't able to login! \n");
        }
    }

    public static void testInvalidUserLogin() {
        //Given
        User user2 = new User("endoplasmic reticulum", "1zx");
        if(user2.checkUserExists() == false){
            System.out.println("Success! Invalid user can't login! \n");
        } else {
            System.out.println("Failure! Invalid User was able to login! \n");
        }
    }

    public static void testManuallyAssignTicket() {
        //create a fake ticket
        Ticket ticket = new Ticket("fakeUser", "Fake Movie", "Fake Cinemas 10 14", "3,4");
//
        //create seat availability for that ticket
        SeatAvailability seatAvailability = new SeatAvailability("Fake Cinemas 10 14");
        seatAvailability.authenticateSeat("Fake Movie");
        seatAvailability.reserveSeat("3,4");
        if (ticket.getStatus().equals("valid") && seatAvailability.getSeatStatus("3").equals("Reserved") && seatAvailability.getSeatStatus("4").equals("Reserved")) {
            System.out.println("Ticket booked successfully! \n");
        }
        else {
            System.out.println("Failure! Ticket created unsuccessfully! \n");
        }

        //clean up
        ticket.cancel();
    }

    //cancel Ticket successfully and seat should become Empty
    public static void testCancelTicket() {
        //create a fake ticket
        Ticket ticket = new Ticket("fakeUser", "Fake Movie", "Fake Cinemas 10 14", "3,4");
        ticket.authenticateTicket();

        //create seat availability for that ticket
        SeatAvailability seatAvailability = new SeatAvailability("Fake Cinemas 10 14");
        seatAvailability.authenticateSeat("Fake Movie");
        seatAvailability.reserveSeat("3,4");


        ticket.cancel();

        //check ticket status and seat status after cancellation
        if (ticket.getStatus().equals("Cancelled") 
            && seatAvailability.getSeatStatus("3").equals("Empty")
            && seatAvailability.getSeatStatus("4").equals("Empty")) {
                System.out.println("Cancel ticket successfully! \n");
            }
        else {
            System.out.println("Cancel ticket fail! \n");
        }
    }

    public static void testManuallyAddingMovieWorks() {
        User user = new User("testingTesterUser", "randPassword");
        user.updateMovieList("LOTR, Oedon Cinemas 180 11", "4", "bought");
        if(user.getPurchase().contains("testingTesterUser,LOTR:: Oedon Cinemas 180 11::4")){
            System.out.println("Success: The intended movie was added");
        } else {
            System.out.println("Failure: The intended movie was NOT added");
        }
    }

    public static void testChangingAccountPassword() {

        String newPassword = "newPassword";

        //create fake user
        User user = new User("testing", "oldPassword");
        user.authenticateUser("999", "fakeemail@fake.com");

        //set his new password
        user.changePassword(newPassword);
        
        //check if password matches with new passowrd
        if (user.getUserPassword().equals(newPassword)) {
            System.out.println("Update new password successfully! \n");
        }
        else {
            System.out.println("New password has not been updated! \n");
        }
    }

    public static void testSeatStoredAreAsExpected() {
        SeatAvailability seatAvailability = new SeatAvailability("Fake Cineamas 333 14");
        String expectedStringAvailability = "0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        if (seatAvailability.getAllAvailableSeatsForAMovie().equals(expectedStringAvailability)) {
            System.out.println("All seats availability is as expected! \n");
        } else {
            System.out.println("All seats availability is NOT as expected! \n");
        }
    }

    public static void testSeatAvailabilityTest() {
        SeatAvailability seatAvailability = new SeatAvailability("Fake Cineamas 333 14");
        if (seatAvailability.getSeatStatus("2").equals("Reserved")){
            System.out.println("Success: Seat that was previously reserved is still shown as reserved");
        } else {
            System.out.println("Failure: Seat that was previously reserved is not shown as reserved");
        }
    }

    public static void testReserveSeat() {
        SeatAvailability seatAvailability = new SeatAvailability("Fake Cineamas 333 14");
        seatAvailability.reserveSeat("36");
        if (seatAvailability.getSeatStatus("36").equals("Reserved")){
            System.out.println("Success: The seat we just reserved was performed successfully");
        } else {
            System.out.println("Failure: The seat we just reserved was NOT performed successfully");
        }

        //cleanup
//        seatAvailability.setFreeSeat("36");
    }

    public static void testUpdateSeatAvailability() {

        //create fake seatAvailability
        SeatAvailability seatAvailability = new SeatAvailability("Fake Cineamas", "333", "14");
        seatAvailability.authenticateSeat("FakeMovieName");

        seatAvailability.reserveSeat("2,36"); //reserve seat 2 and 36
        seatAvailability.setFreeSeat("36"); //then empty seat 36
        
        //check to see if seat 2 is reserved and seat 36 is empty
        if (seatAvailability.getSeatStatus("2").equals("Reserved")
            && seatAvailability.getSeatStatus("36").equals("Empty")) 
        {
            System.out.println("Update seat availability successfully! \n");
        }
        else {
            System.out.println("Updating seats fails! \n");
        }
    }

    public static void main (String[] args) { 
        testBlockingUser();
        testCancelTicket();
        testValidUserLogin();
        testInvalidUserLogin();
        testUpdateSeatAvailability();
        testChangingAccountPassword();
        testManuallyAssignTicket();
        testSeatStoredAreAsExpected();
        testSeatAvailabilityTest();
        testReserveSeat();
        testGettingUserNameWorks();
        testChangeUserPasswordIsSuccessful();
        testUserStatusIsAccurateForActiveUser();
        testUserStatusIsAccurateForBlockedUser();
        testGettingUserIDWorks();
        testUserPurchasedMoviesAreAccurate();
        testManuallyAddingMovieWorks();
        testUserSignup();
    }
}
