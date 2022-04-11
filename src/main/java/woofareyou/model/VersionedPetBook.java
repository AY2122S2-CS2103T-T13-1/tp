package woofareyou.model;

import static java.util.logging.Level.INFO;

import java.util.ArrayList;
import java.util.logging.Logger;

public class VersionedPetBook extends PetBook {

    private static final ArrayList<ReadOnlyPetBook> petBookStateList = new ArrayList<>();
    private Integer currentStatePointer = 0;
    private final Logger logger = Logger.getLogger(VersionedPetBook.class.getName());


    public VersionedPetBook() {}

    /** Creates a VersionedPetBook using the Pets in the {@code toBeCopied}
     */
    public VersionedPetBook(ReadOnlyPetBook toBeCopied) {
        this();
        petBookStateList.add(new PetBook(toBeCopied));
    }

    /** Commits a new petBook state to the petBookStateList
     * @param currentPetBookState current pet book state
     */
    public void commit(ReadOnlyPetBook currentPetBookState) {
        petBookStateList.add(new PetBook(currentPetBookState));
        this.currentStatePointer = petBookStateList.size() - 1;
    }

    /**
     * Shifts the current state pointer to the left.
     * @return The previous state of the pet book.
     */
    public ReadOnlyPetBook undo() throws Exception {
        System.out.println(petBookStateList.size());
        if (petBookStateList.size() <= 1) {
            throw new Exception("No commands to undo");
        }

        logger.log(INFO, String.valueOf(petBookStateList.size()));
        this.currentStatePointer -= 1;
        petBookStateList.remove(this.currentStatePointer + 1);
        return petBookStateList.get(this.currentStatePointer);
    }
}
