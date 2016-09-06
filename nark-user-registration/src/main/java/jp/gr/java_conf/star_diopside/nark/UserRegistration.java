package jp.gr.java_conf.star_diopside.nark;

import java.io.Console;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import jp.gr.java_conf.star_diopside.nark.service.UserService;

@Service
public class UserRegistration implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Options opt = new Options();
        opt.addOption(Option.builder("u").required().hasArg().argName("username").desc("ユーザ名を指定する。").build());
        opt.addOption(Option.builder("a").hasArg().argName("authority").desc("ユーザに設定する権限を指定する。").build());
        opt.addOption(Option.builder("p").hasArg().argName("password").desc("ユーザパスワードを指定する。").build());

        DefaultParser parser = new DefaultParser();
        CommandLine cl;

        try {
            cl = parser.parse(opt, args);
        } catch (ParseException e) {
            new HelpFormatter().printHelp("nark-user-registration -u <username> [-a <authority>] [-p <password>]", opt);
            return;
        }

        String username = cl.getOptionValue("u");
        String authority = cl.getOptionValue("a", "ROLE_USER");
        String password = Optional.ofNullable(cl.getOptionValue("p")).orElseGet(() -> {
            Console console = System.console();
            return String.valueOf(console.readPassword("パスワード: "));
        });

        userService.createUser(username, () -> password, authority);
    }
}
