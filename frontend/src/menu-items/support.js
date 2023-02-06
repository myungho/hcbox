// assets
import { ChromeOutlined, QuestionOutlined } from '@ant-design/icons';

// icons
const icons = {
    ChromeOutlined,
    QuestionOutlined
};

// ==============================|| MENU ITEMS - SAMPLE PAGE & DOCUMENTATION ||============================== //

const support = {
    id: 'support',
    title: 'Support',
    type: 'group',
    children: [
        {
            id: 'sample-page',
            title: 'Sample Page',
            type: 'item',
            url: '/sample',
            icon: icons.ChromeOutlined
        },
        {
            id: 'demo',
            title: 'Demo',
            type: 'item',
            url: 'https://mantisdashboard.io/free',
            icon: icons.QuestionOutlined,
            external: true,
            target: true
        }
    ]
};

export default support;
