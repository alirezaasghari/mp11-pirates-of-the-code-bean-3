package at.refugeescode.mp11piratesofthecodebean3.logic;

import at.refugeescode.mp11piratesofthecodebean3.persistence.PieceOfEightRepository;
import at.refugeescode.mp11piratesofthecodebean3.persistence.Pirate;
import at.refugeescode.mp11piratesofthecodebean3.persistence.PirateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class PirateService {

    private PirateRepository pirateRepository;

    private PieceOfEightRepository pieceOfEightRepository;

    private CsvParser csvParser;

    public PirateService(PirateRepository pirateRepository, PieceOfEightRepository pieceOfEightRepository, CsvParser csvParser) {
        this.pirateRepository = pirateRepository;
        this.pieceOfEightRepository = pieceOfEightRepository;
        this.csvParser = csvParser;
    }

    public void populatePirates() {

        // delete all the pirates and pieces of eight from the database
        deleteAll();

        // use the csvParser to get a list of all the pirates, the path should be "classpath:pirates.csv"
        PirateModule pirateModule = new PirateModule("classpath:pirates.csv");
        List<Pirate> pirates = csvParser.asList(pirateModule);

        // for each pirate, save first manually the piece of eight,
        // connect it to the corresponding pirate and then save the pirate
        pirates.stream()
                .map(savePieceOfEight())
                .forEach(pirateRepository::save);
    }

    private Function<Pirate, Pirate> savePieceOfEight() {
        return pirate -> {
            pieceOfEightRepository.save(pirate.getPieceOfEight());
            return pirate;
        };
    }

    public List<Pirate> findAll() {
        return pirateRepository.findAll(); // return all the pirates from the database
    }

    public void deleteAll() {

        // delete all pirates
        pirateRepository.deleteAll();

        // delete all pieces of eight
        pieceOfEightRepository.deleteAll();
    }
}
