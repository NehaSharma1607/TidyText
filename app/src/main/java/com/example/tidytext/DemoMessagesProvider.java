package com.example.tidytext;

import java.util.ArrayList;
import java.util.List;

public class DemoMessagesProvider {

    public static List<Message> getSampleMessages() {
        List<Message> messages = new ArrayList<>();

        // Personal
        messages.add(new Message("Mom", "Don’t forget to call grandma today!", "Personal"));
        messages.add(new Message("Priya", "Let’s meet for coffee tomorrow at 5?", "Personal"));
        messages.add(new Message("Sister", "Renewed you Netflix subscription.", "Personal"));

        // Govt
        messages.add(new Message("UIDAI", "Your Aadhaar OTP is 785123. Valid for 10 minutes.", "Govt"));
        messages.add(new Message("Election Comm", "Voter ID verification drive begins next week.", "Govt"));
        messages.add(new Message("mParivahan", "Your vehicle insurance expires soon. Renew on time.", "Govt"));
        messages.add(new Message("IRCTC", "Maintenance downtime from 1–3 AM on 3rd Nov.", "Govt"));

        // Websites Alerts
        messages.add(new Message("SSC", "New SSC CGL 2025 Tier-1 exam dates announced on ssc.gov.in", "Website Alerts"));
        messages.add(new Message("UPSC", "UPSC Civil Services 2025 prelims admit card released.", "Website Alerts"));
        messages.add(new Message("NTA", "JEE Main 2025 registration starts on jeemain.nta.ac.in", "Website Alerts"));
        messages.add(new Message("NTA", "NEET 2025 application window opens till 15 Dec.", "Website Alerts"));

        // Bookings
        messages.add(new Message("IRCTC", "Train 12627 departs at 06:35 AM tomorrow from Chennai Central.", "Bookings"));
        messages.add(new Message("MakeMyTrip", "Your hotel booking for Goa from 3–5 Nov is confirmed.", "Bookings"));
        messages.add(new Message("IndiGo", "Flight 6E-513 to Mumbai confirmed. Web check-in open.", "Bookings"));
        messages.add(new Message("Swiggy", "Your Swiggy order will be delivered by 8 PM.", "Bookings"));
        messages.add(new Message("Amazon", "Your order for Bluetooth speaker has been shipped.", "Bookings"));
        messages.add(new Message("Zomato", "Your delivery partner has picked up your order.", "Bookings"));

        // Recharge
        messages.add(new Message("Airtel", "Your ₹299 recharge successful. 1.5GB/day valid for 28 days.", "Recharge"));
        messages.add(new Message("Jio", "Your data pack is expiring soon. Recharge now to stay connected.", "Recharge"));
        messages.add(new Message("Paytm", "You received ₹20 cashback for recharging your mobile!", "Recharge"));

        // Transactions
        messages.add(new Message("HDFC Bank", "₹1,250 debited from A/C ending 2354 on 30-Oct. Avl Bal ₹12,580.", "Transactions"));
        messages.add(new Message("SBI", "₹50,000 credited to your account by NEFT from ABC Ltd.", "Transactions"));
        messages.add(new Message("ICICI", "Payment of ₹999 to Amazon has been completed via UPI.", "Transactions"));
        messages.add(new Message("Axis Bank", "₹5,000 withdrawn from ATM at MG Road.", "Transactions"));
        messages.add(new Message("Paytm Wallet", "₹100 added to your wallet from credit card.", "Transactions"));

        // Spam / Promotions
        messages.add(new Message("Dream11", "Win ₹1 crore this IPL season! Join the mega contest now.", "Spam"));
        messages.add(new Message("UrbanClap", "Flat ₹200 off on your next salon service. Book today!", "Spam"));
        messages.add(new Message("H&M", "Exclusive preview for members. Visit your nearest store.", "Spam"));
        messages.add(new Message("Flipkart", "Big Diwali sale is live! Discounts on electronics and more.", "Spam"));

        // Travel updates
        messages.add(new Message("Ola", "Your driver Ajay (KA04 1234) has arrived. Trip fare ₹225.", "Bookings"));
        messages.add(new Message("Uber", "Trip completed. You’ve earned 25 reward points.", "Bookings"));
        messages.add(new Message("RedBus", "Bus from Bangalore to Hyderabad departs at 11 PM. Seat 21 confirmed.", "Bookings"));

        return messages;
    }
}

