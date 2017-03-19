# Weiver - a Review Aggregation and Analytics Program
Our group hackathon project at Kent Hack Enough hackathon 2016.

Weiver is a terminal-based proof-of-concept review aggregation and analytics
program.  Given the name of a technology product as input, Weiver searches for
the appropriate webpages on various technology review sites and pulls the
review body as well as relevant data such as the review title, url, and
common sections such as the pros and cons section.  It then searches for a list
of keywords and counts their frequency, as well as determining the most common
terms that appear in a given article excluding common words (and, the, etc;
what words to exclude are specified in a json file).  Finally, it aggregates the
text from across reviews and similarly analyzes that.  The final output, the
result of Weiver's analysis, is formatted and printed to the user.
