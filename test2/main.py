import re

def groupWords(input: str) -> list[str]:
    output = []
    pattern = re.compile(r"([a-zA-Z]+|\d+)")
    for match in pattern.finditer(input):
        output.append(match.group())
    return output


def generateAllVariations(input: str) -> list[str]:
    clusters = groupWords(input)

    variations = []

    def backTrack(index: int, current: str):
        if index == len(clusters):
            variations.append("".join(current))
            return

        backTrack(index + 1, current + clusters[index])
        backTrack(index + 1, current + "-" + clusters[index])

    backTrack(1, clusters[0])

    return variations


def containsAlphaNumeric(s: str) -> bool:
    pattern = re.compile(r".*[a-zA-Z].*\d.*|\d.*[a-zA-Z].*")
    return bool(pattern.match(s))


def getVariations(words: list[str]) -> list[str]:
    variations = []
    for word in words:
        variations.extend(generateAllVariations(word))
    return variations


def containsAlphaNumeric(s: str) -> bool:
    pattern = re.compile(r".*[a-zA-Z].*\d.*|\d.*[a-zA-Z].*")
    matcher = pattern.match(s)
    return bool(matcher)


def findWordsWithNumbersAndLatinCharacters(sentence: str) -> list[str]:
    sentence = sentence.strip()
    words = sentence.split()

    resultList = []
    for word in words:
        if containsAlphaNumeric(word):
            resultList.append(word)

    return resultList


# main
input_str = "Products are created with 132cxvx SKUs and MXX and CSV and 79 and mic7979 and m98opt options"

matched_words = findWordsWithNumbersAndLatinCharacters(input_str)
variations = getVariations(matched_words)

print(f"Matched Words: '{matched_words}'")

print(
    f"Variations with '-' character: '{[item for item in variations if not item in matched_words]}'"
)
