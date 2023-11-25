Feature: Search on the articles page

  Background:
    Given the 'Main' site is opened

  Scenario Outline: Search for <cardName> articles
    Given the 'Articles' button is clicked
    When I search for '<cardName>'
    Then I see <cardCount> article card
    And All cards contain the '<cardName>' word

    Examples:
      | cardName  | cardCount |
      | Idea Pool | 6         |
      | Apple     | 3         |
      | Quiz      | 1         |
      | Bank      | 4         |
      | Android   | 4         |




