export default class Site {
    constructor(
        public version ?: string,
        public head ?: string,
        public body ?: string,
        public title ?: string,
        public headingTags ?: Map<string, string>,
        public hasLoginForm ?: boolean,
        public internalLinks ?: number,
        public externalLinks ?: number,
        public hyperLinksMap ?: Map<string, string>
    ) {
    }
}