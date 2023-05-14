with open("words_alpha.txt", "r") as file:
    words = file.readlines()

five_letter_words = [word.strip() for word in words if len(word.strip()) == 5]

with open("five_letter_words.txt", "w") as output:
    for word in five_letter_words:
        output.write(f"{word}\n")
