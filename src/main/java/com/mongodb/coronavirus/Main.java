package com.mongodb.coronavirus;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.OffsetDateTime;
import java.util.Scanner;

public class Main extends ListenerAdapter {

    String PREFIX = "~";
    userMap users = new userMap();

    public static void main(String[] args)
    {
        //We construct a builder for a BOT account. If we wanted to use a CLIENT account
        // we would use AccountType.CLIENT
        try
        {
            String token = System.getenv("TOKEN");
            JDA jda = JDABuilder.createDefault(token) // The token of the account that is logging in.
                    .addEventListeners(new Main())   // An instance of a class that will handle events.
                    .build();
            jda.awaitReady(); // Blocking guarantees that JDA will be completely loaded.
            jda.addEventListener(new Detect());
            jda.addEventListener(new DetectMessage());
            jda.addEventListener(new DetectOnOff());
            System.out.println("Finished Building JDA!");
        }
        catch (LoginException e)
        {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            //Due to the fact that awaitReady is a blocking method, one which waits until JDA is fully loaded,
            // the waiting can be interrupted. This is the exception that would fire in that situation.
            //As a note: in this extremely simplified example this will never occur. In fact, this will never occur unless
            // you use awaitReady in a thread that has the possibility of being interrupted (async thread usage and interrupts)
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        //Send message if last event from this user happened more than 4 hours ago
        if(handWashReminder(event)){
            event.getChannel().sendMessage(washHands().build()).queue();
        }

        System.out.println("We received a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay());

        if(isBotCommand(event)){
            if(event.getMessage().getContentRaw().substring(0,6).equalsIgnoreCase(PREFIX + "cases")){
            String arg[] = event.getMessage().getContentRaw().substring(7,event.getMessage().getContentRaw().length()).split(",");
            event.getChannel().sendMessage(getCases(new MongoDB(arg[0],arg[1].trim()).getData()).build()).queue();
            }
        }
        users.addLastMessage(event.getAuthor().getAvatarId(),event);
    }

    public static String selectActivity() throws FileNotFoundException {
        File file = new File("C:\\Users\\puza7\\IdeaProjects\\covid19DiscordBot\\Assets\\Activities");
        Scanner scan = new Scanner(file);
        int lineNumber = 1;
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println("line " + lineNumber + " :" + line);
            lineNumber++;
        }
        return "lol";
    }

    public boolean isBotCommand(MessageReceivedEvent event){
        if(!event.getAuthor().isBot()) {
            return event.getMessage().getContentRaw().substring(0, 1).equals(PREFIX);
        }
        else{
            return false;
        }
    }

    public EmbedBuilder getCases(String s){
        String [] ar = s.split(",");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Data:");
        embed.addField(ar[0].substring(0,9),ar[0].substring(10),false);
        embed.addField(ar[1].substring(0,7),ar[1].substring(8),false);
        embed.addField(ar[2].substring(0,5),ar[2].substring(6),false);
        embed.setFooter("Source: John Hopkins University");
        return embed;
    }
    public boolean handWashReminder(MessageReceivedEvent event){
        if(users.getLastMessage(event.getAuthor().getAvatarId()) != null) {
            OffsetDateTime pastMessage = users.getLastMessage(event.getAuthor().getAvatarId()).getMessage().getTimeCreated();
            OffsetDateTime latestMessage = event.getMessage().getTimeCreated();

            if (pastMessage.plusMinutes(1).compareTo(latestMessage) < 0) {
                return true;
            }
        }
        return false;
    }

    public EmbedBuilder washHands(){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Reminder!");
        embed.addField("Don't forget to wash your hands","It is important that we do our parts fighting this pandemic",false);
        embed.setFooter("Best regards,\nCovid-19 Discord Bot");
        return embed;
    }

}
