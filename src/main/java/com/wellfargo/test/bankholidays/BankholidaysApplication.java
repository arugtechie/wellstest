package com.wellfargo.test.bankholidays;

import com.wellfargo.test.bankholidays.client.NagerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Objects;

import static java.lang.System.exit;

@SpringBootApplication
public class BankholidaysApplication implements CommandLineRunner {

	public static String year;
	public static String countryCode;
	@Autowired
	NagerClient nagerClient;

	private static Logger LOG = LoggerFactory
			.getLogger(BankholidaysApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankholidaysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("EXECUTING : BankholidaysApplication");

		setInputArguments(args);

		LOG.info("Executing getListofPublicHolidays :");
		System.out.println(nagerClient.getListofPublicHolidays(year, countryCode));

		System.out.print(args[2] + (nagerClient.IsTodayInPublicHolidays(args[2], args[1])
				? (" IS ") : (" IS NOT")));
		System.out.print(" a holiday in the country " + args[1]);


		exit(0);

	}

	private static String setInputArguments(String[] args) {

		if(Objects.isNull(args[0]) || args[0].isBlank()) {
			LocalDate localDate = LocalDate.now();
			year = String.valueOf(localDate.getYear());
		}else
		{
			year = args[0];
		}

		if(Objects.isNull(args[1]) || args[1].isBlank()) {
			//default country code
			countryCode = "us";
		}else
		{
			countryCode = args[1];
		}

		if(Objects.isNull(args[2]) || args[0].isBlank()) {
			//LocalDate localDate = LocalDate.now().;
			//
		}

		return countryCode;
	}
}
