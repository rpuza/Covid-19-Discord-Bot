import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, FileNotFoundException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NzUzNjk5NjUzODE1NTAwOTIw.X1p_gA.pRobNCcl11A_kPjrOvivZnCy3sg";
        System.out.println(selectActivity());
        /*builder.setToken(token);
        builder.addEventListener(new main());
        builder.buildAsync();*/
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay());
        if(event.getMessage().getContentRaw().equals("Say Hello!")){
            event.getChannel().sendMessage("Hello World!").queue();
        }
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
}
