# simplevirtualqueuesystem

This is a simple virtual queuing system that mimics Yelps waitlist feature. Implemented are APIs the client side can trigger.

## Usage

###### (Note, this application has not been set up with a Dockerfile as of yet)
1. Run the application
   2. If running locally, its endpoint is likely localhost:8080
2. `/joinQueue` takes a `name` and `numGuests`. This adds a user to the queue
3. `/getQueue` returns the current queue. You will see you position in the queue here
4. `/getNextGuest` grabs the next person from the queue and updates everyones position and notifies them through Server Sent Events. 

Note that there are no server side actions performed on this user, but you can easily add that logic.
